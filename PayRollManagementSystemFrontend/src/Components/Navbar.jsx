import { Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";

const Navbar = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loggedInUser = JSON.parse(localStorage.getItem("user"));
    setUser(loggedInUser);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    setUser(null);
    navigate("/"); // redirect to home
  };

  return (
    <nav className="navbar navbar-expand-lg bg-primary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand fw-bold text-white fst-italic" to="/">
          Payroll Management System
        </Link>

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto align-items-center">
            {user ? (
              <>
                {/* Welcome message */}
                <li className="nav-item me-3 text-white">
                  Welcome, {user.firstName || user.email} ({user.roles?.[0]})
                </li>

                {/* Dashboard links */}
                {user.roles?.includes("ADMIN") && (
                  <li className="nav-item me-2">
                    <Link className="btn btn-outline-light btn-sm" to="/admin">
                      Admin Dashboard
                    </Link>
                  </li>
                )}
                {user.roles?.includes("EMPLOYEE") && (
                  <li className="nav-item me-2">
                    <Link className="btn btn-outline-light btn-sm" to="/employee">
                      Employee Dashboard
                    </Link>
                  </li>
                )}

                {/* Logout button */}
                <li className="nav-item">
                  <button className="btn btn-outline-light btn-sm" onClick={handleLogout}>
                    Logout
                  </button>
                </li>
              </>
            ) : null}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
