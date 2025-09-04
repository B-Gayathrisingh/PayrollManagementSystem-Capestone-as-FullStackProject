import { Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import Signup from "./Components/Signup";
import Login from "./Components/Login";
import AdminDashboard from "./Components/AdminDashboard";
import EmployeeDashboard from "./Components/EmployeeDashboard";
import LeaveRequest from "./Components/LeaveRequest";
import SalarySlip from "./Components/SalarySlip";
import { EmployeeProvider } from "./Components/EmployeeContext"; // import your context

function App() {
  return (
    <>
      <Navbar />
      <div className="container py-4">
        <EmployeeProvider>
          <Routes>
            <Route path="/" element={<Home />} />          {/* Home page */}
            <Route path="/signup" element={<Signup />} />  {/* Signup page */}
            <Route path="/login" element={<Login />} />    {/* Login page */}
            <Route path="/admin" element={<AdminDashboard />} />
            <Route path="/employee" element={<EmployeeDashboard />} />
            <Route path="/employee/leave" element={<LeaveRequest />} />
            <Route path="/employee/salary" element={<SalarySlip />} />
          </Routes>
        </EmployeeProvider>
      </div>
    </>
  );
}

export default App;
