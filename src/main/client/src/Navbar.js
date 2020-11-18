// Statless Functional Component
import logo from './logo.svg';

const Navbar = ({totalCounters}) => {
  return (
    <nav class="navbar navbar-light bg-light navbar-toggler">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src={logo} alt="logo" width="40" height="40" class="d-inline-block"/>
            Navbar {" "}
            <span className="badge badge-primary rounded-pill text-dark">
              {totalCounters}
            </span>
        </a>
        <form class="d-flex">
          <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search"/>
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;