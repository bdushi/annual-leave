import React, { useContext } from "react";
import { SearchContext } from "./context/SearchContext";
import logo from "./assets/logo.svg";

const Navbar = () => {
  const { onSearch } = useContext(SearchContext);

  return (
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid">
          <a className="navbar-brand" href="/">
            <img src={logo} alt="logo" width="50" height="50" className="d-inline-block"/>
            Leaves
          </a>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="navbar-nav mr-auto mb-2 mb-lg-0">
              <a className="nav-link">Profile</a>
              <a className="nav-link">Pricing</a>
              <a className="nav-link">Disabled</a>
          </div>
          <form className="d-flex">
            <input 
              className="form-control mr-2" 
              type="search" 
              placeholder="Search" 
              aria-label="Search"
              onChange={event => onSearch(event.target.value)}/>
              {/* <button 
                className="btn btn-outline-success" 
                type="submit"
                onClick={() => searchTerm(search)}>
                  Search
              </button> */}
          </form>
        </div>
    </nav>
  );
}

export default Navbar;