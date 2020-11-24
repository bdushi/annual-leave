import { Redirect, Route } from "react-router-dom"
import auth from "./Auth";

export const SecureRoute = ({ component: Component, ...rest}) => {
    return (
        <Route 
          {...rest} 
          render={(props) => (
            auth.isAuthenticated === false
              ? <Component {...props} />
              : <Redirect 
              to = { {
                pathname: '/signIn',
                state: { from: props.location }
              }
            } />
        )
        
  // return (
    //     <Route {...rest} render={(props) => (
    //         auth.isAuthenticated === false
    //           ? <Component {...props} />
    //           : <Redirect to='/signIn' />
    //       )} />
    // )
    
    // return auth.isAuthenticated()
    //   ? (<Redirect to={{ pathname: '/signIn' }} />)
    //   : (<Component />) ;
  }