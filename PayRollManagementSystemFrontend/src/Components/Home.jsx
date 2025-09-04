import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  const containerStyle = {
    minHeight: "100vh",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    color: "white",
    textAlign: "center",
    backgroundImage:
      "linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://images.unsplash.com/photo-1581091012184-8b5fddc9f1fc?auto=format&fit=crop&w=1470&q=80')",
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    padding: "20px",
  };

  const headingStyle = {
    fontSize: "3rem",
    marginBottom: "20px",
  };

  const paragraphStyle = {
    fontSize: "1.2rem",
    marginBottom: "40px",
  };

  const buttonStyle = {
    padding: "10px 20px",
    margin: "0 10px",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    fontSize: "1rem",
  };

  const loginButtonStyle = {
    ...buttonStyle,
    backgroundColor: "#007bff",
    color: "white",
  };

  const registerButtonStyle = {
    ...buttonStyle,
    backgroundColor: "#28a745",
    color: "white",
  };

  return (
    <div style={containerStyle}>
      <h1 style={headingStyle}>Welcome to Payroll Management System</h1>
      <p style={paragraphStyle}>Manage employees, payroll, and profiles efficiently.</p>
      <div>
        <button style={loginButtonStyle} onClick={() => navigate("/login")}>
          Login
        </button>
        <button style={registerButtonStyle} onClick={() => navigate("/signup")}>
          Register
        </button>
      </div>
    </div>
  );
};

export default Home;
