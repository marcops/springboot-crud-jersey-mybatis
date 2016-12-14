package br.com.adeptsd.product.filter;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

import br.com.adeptsd.product.Authorization;
import br.com.adeptsd.product.entity.User;

@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	
	 	@Context
	    private ResourceInfo resourceInfo;
	    @Autowired Authorization authorization;
	    private static final String AUTHORIZATION_PROPERTY = "Authorization";
	    private static final String AUTHENTICATION_SCHEME = "Basic";
	      
	    @Override
	    public void filter(ContainerRequestContext requestContext)
	    {
	        Method method = resourceInfo.getResourceMethod();
	        //Access allowed for all
	        if( ! method.isAnnotationPresent(PermitAll.class))
	        {
	            //Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	            	//
	                //requestContext.abortWith(ACCESS_FORBIDDEN);
	            	requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
	                        .entity("FORBIDDEN!")
	                        .build());
	                return;
	            }
	              
	            //Fetch authorization header
	            String tokenSecured = getAuthToken(requestContext);
	            String token = authorization.authorize(tokenSecured);
	              
	            //If no authorization information present; block access
	            if(token == null || token.isEmpty())
	            {
	            	requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
	                        .entity("UNAUTHORIZED!")
	                        .build());
	                return;
	            }
	            if(method.isAnnotationPresent(RolesAllowed.class) )
	          { 
	            	User u = new User();
		            try {
			            u = u.fromString(token,User.class);
		            }catch(Exception e) {
		            	  requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
	                              .entity("INVALID USER").build());
		                    return;
		            }
		            //Verify user access
		            String clazz = method.getDeclaringClass().toString();
		            String founded = u.getCategories().stream().filter(x -> clazz.contains(x)).findFirst().get();
		          if(founded!= null) {
		        	  return;
		          }
		          requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                          .entity("DENIED").build());
                  return;
	            	 //  RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	            //if(rolesAnnotation != null){
                  //if(rolesAnnotation == admin)
	            	// Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
		               // RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
		                //Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                  //rolesSet.contain(admin)
		                //Is user valid?
		               /* if( ! isUserAllowed(username, password, rolesSet))
		                {
		                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
	                                .entity("DENIED").build());
		                    return;
		                }*/
		           // }
	            }
	        }
	    }
	  
	    
	    private String getAuthToken(ContainerRequestContext requestContext) {
	        String token = requestContext.getHeaderString(AUTHORIZATION_PROPERTY);
	        if(isBasicToken(token))
	             return token.split(" ")[1];                
	        return "";
	    }
	    private boolean isBasicToken(String token) {
	        return token != null && !token.isEmpty() && token.split(" ").length == 2 && token.split(" ")[0].equals(AUTHENTICATION_SCHEME);
	    }
    /*private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Authorization authorization;

    @Context
    private HttpServletRequest request;
    
    @Context
    private ResourceInfo resourceInfo;*/

   /* @Override
    public void filter(ContainerRequestContext requestContext) {
    	if(operationRequiresAuthorization(resourceInfo)){
	        try {
	            log.info("AuthFilter ...");
	            String token = getAuthToken(requestContext);
	            SecurityContextUser contextUser = new SecurityContextUser(authorization.authorize(token));
	            SecurityContext originalContext = requestContext.getSecurityContext();
	            Set<String> roles = new HashSet<>();
	            roles.add(Authorization.authenticatedRole);
	            requestContext.setSecurityContext( );
                } catch (ExpiredJwtException e){
                    printRequest(request, requestContext, e);
                    throw e;
	            //throw new UnauthorizedException("The access token expired");
                } catch (Exception e) {
	            printRequest(request, requestContext, e);
	            throw e;
	           // throw new UnauthorizedException("Authorization Required");
	        }
    	}
    }
*/
    
   /* private String getAuthToken(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString(Authorization.authHeader);
        if(isBearerToken(token))
             return token.split(" ")[1];                
        return "";
    }

    private boolean isBearerToken(String token) {
        return token != null && !token.isEmpty() && token.split(" ").length == 2 && token.split(" ")[0].equals("Bearer");
    }

    */
    
    /*private Boolean operationRequiresAuthorization(ResourceInfo resourceInfo){
    	return resourceInfo.getResourceMethod().getDeclaredAnnotation(RolesAllowed.class) != null;
    }*/

}
