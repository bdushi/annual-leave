
import React, { useState } from "react"
import Home from './Home';
import Navbar from './Navbar';
import SignIn from './SignIn';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect
} from "react-router-dom";
import { ProtectedRoute } from "./ProtectedRoute";
import { LeavesApproved } from "./LeavesApproved"
import SearchProvider from "./context/SearchContext";

function App() {
  // const [search, setSearch] = useState(null);
  // const onSearch = (search) => {
  //   setSearch(search)
  
  // };
  // <React.Fragment>
  //     <Navbar/>
  //       <main className="container">
  //         <Home/>
  //       </main>
  //     </React.Fragment>

  // <div>
  //   <div className="container">
  //     <Navbar />
  //     <Route exact path="/" component={Home}/>
  //   </div>
  //   </div>
  const LoginContainer = () => (
    <div>
      <Route exact path="/" render = {() => <Redirect to="signIn"/>}/>
      <Route exact path="/signIn" component={SignIn}/>
    </div>
  )

  const AppContainer = () => (
    <SearchProvider>
      <React.Fragment>
        <Navbar/>
        <main className="container">
          <Route exact path="/" component={Home}/>
          {/* <Route exact path="/" component={props => <Home search={search} {...props}/>}/> */}
          <Route exact path="/leavesApproved" component={LeavesApproved}/>
        </main>
      </React.Fragment>
    </SearchProvider>
  )

  const DefaultContainer = () => (
    <div className="container">
      <Navbar />
      <main className="container">
        <Route exact path="/" component={Home}/>
      </main>
    </div>
 )

  
  return (
    <Router>
      <Switch>
        {/* <Route exact path="/(signIn)" component={LoginContainer}/> */}
        <Route exact path="/signIn" component={SignIn}/>
        <ProtectedRoute component={AppContainer}/>
      </Switch>
    </Router>
  );
}

export default App;
