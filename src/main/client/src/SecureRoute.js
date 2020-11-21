import { Redirect, Route } from "react-router-dom"
import auth from "./Auth";

export const SecureRoute = ({ component: Component, ...rest}) => {
    return (
        <Route 
            {...rest} 
            render = { props => {
                if(auth.isAuthenticated) {
                    return <Component {...props}/>
                } else {
                    return (
                        <Redirect
                            to={{
                                pathname: "/",
                                state: {
                                    form: props.location
                                }
                            }}
                        />
                    );
                }
            }}
        />)
  }