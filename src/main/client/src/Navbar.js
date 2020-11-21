// Statless Functional Component
import logo from './logo.svg';
import { Link } from 'react-router-dom';

const Navbar = ({totalCounters}) => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <a className="navbar-brand mb-0 h1" href="#">
            <img src={logo} alt="logo" width="50" height="50" className="d-inline-block"/>
            Leaves
        </a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="mr-auto collapse navbar-collapse" id="navbarNav">
          <div className="navbar-nav">
            <a className="nav-link">Profile</a>
            <a className="nav-link">Pricing</a>
            <a className="nav-link">Disabled</a>
          </div>
        </div>
        <form className="d-flex">
          <input className="form-control mr-2" type="search" placeholder="Search" aria-label="Search"/>
          <button className="btn btn-outline-success" type="submit">Search</button>
        </form>
        <ul className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a className="dropdown-item" href="#">Action</a></li>
            <li><a className="dropdown-item" href="#">Another action</a></li>
            <li><a className="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;