import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "./FetchApi"; // uses userApi pointing to User service

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
    role: ""
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    if (!formData.role) {
      alert("Please select a role");
      return;
    }

    try {
      // Send roles as an array to match backend UserDto
      await registerUser({
        email: formData.email,
        password: formData.password,
        roles: [formData.role] // fix: wrap selected role in array
      });

      alert("Registration successful! Please login.");
      navigate("/login");
    } catch (error) {
      console.error("Registration error:", error.response || error);
      alert("Registration failed: " + (error.response?.data?.message || error.message));
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center bg-light min-vh-100">
      <div className="card shadow-lg p-4" style={{ width: "400px", borderRadius: "12px" }}>
        <h3 className="text-center mb-3">Register</h3>
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            name="email"
            className="form-control mb-3"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            name="password"
            className="form-control mb-3"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            name="confirmPassword"
            className="form-control mb-3"
            placeholder="Confirm Password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />

          <select
            name="role"
            className="form-control mb-4"
            value={formData.role}
            onChange={handleChange}
            required
          >
            <option value="" disabled>
              Select Role
            </option>
            <option value="EMPLOYEE">Employee</option>
            <option value="ADMIN">Admin</option>
          
          </select>

          <button type="submit" className="btn btn-success w-100 pt-2">
            Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default Register;
