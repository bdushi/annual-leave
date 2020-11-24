import { Redirect, Route } from "react-router-dom"
import auth from "./Auth";

export const SignInRoute = ({ component: Component, ...rest}) => {
    return (
        <Route {...rest} render={(props) => (
            auth.isAuthenticated === true
              ? <Component {...props} />
              : <Redirect to='/' />
          )} />
    )
  }