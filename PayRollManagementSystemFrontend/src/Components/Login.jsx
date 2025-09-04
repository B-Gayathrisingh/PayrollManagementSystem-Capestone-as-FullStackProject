import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "./FetchApi";

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!email || !password) return alert("Enter email & password");

    setLoading(true);
    try {
      const payload = { email, password };
      console.log("Sending login payload:", payload);

      const user = await loginUser(payload);
      console.log("Login response:", user);

      if (!user || !user.token) {
        alert("Invalid credentials or backend response format");
        return;
      }

      localStorage.setItem("token", user.token);
      localStorage.setItem("user", JSON.stringify(user));

      const roles = user.roles || [];
      if (roles.includes("ADMIN")) navigate("/admin");
      else if (roles.includes("EMPLOYEE")) navigate("/employee");
      else alert("No valid role assigned to this user");

    } catch (error) {
      console.error("Login failed:", error.response || error);
      const message = error.response?.data?.message || error.message || "Login failed";
      alert(message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div className="card shadow-lg p-4" style={{ width: "400px", borderRadius: "12px" }}>
        <h3 className="text-center mb-3">Login</h3>
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            className="form-control mb-4"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            className="form-control mb-4"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button className="btn btn-primary w-100" type="submit" disabled={loading}>
            {loading ? "Logging in..." : "Login"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
