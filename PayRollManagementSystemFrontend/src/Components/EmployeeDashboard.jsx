import React, { useState, useEffect } from "react";
import { createEmployee, getAllEmployees, applyLeave, getLeaveById } from "./FetchApi";

const EmployeeDashboard = () => {
  const user = JSON.parse(localStorage.getItem("user"));

  const [employee, setEmployee] = useState(null);
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    dob: "",
    phone: "",
    address: "",
    department: "",
    designation: "",
    salary: "",
    employeeId: "",
    email: user?.email || "",
  });

  const [leaveForm, setLeaveForm] = useState({
    startDate: "",
    endDate: "",
    reason: "",
    employeeId: "",
  });

  const [leaves, setLeaves] = useState([]);

  // Fetch employee details
  const fetchEmployee = async () => {
    try {
      const allEmployees = await getAllEmployees();
      const emp = allEmployees.find((e) => e.userid === user?.id);
      if (emp) {
        setEmployee(emp);
        setLeaveForm((prev) => ({ ...prev, employeeId: emp.employeeId }));
        fetchLeaves(emp.employeeId);
        localStorage.setItem(`employee_${user?.id}`, JSON.stringify(emp));
      }
    } catch (err) {
      console.error("Error fetching employee:", err);
    }
  };

  const fetchLeaves = async (employeeId) => {
    try {
      const data = await getLeaveById(employeeId);
      setLeaves(data || []);
    } catch (err) {
      console.error("Error fetching leaves:", err);
    }
  };

  useEffect(() => {
    if (user?.id) fetchEmployee();
  }, [user]);

  const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });
  const handleLeaveChange = (e) => setLeaveForm({ ...leaveForm, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const payload = { ...formData, userid: user?.id };
      const newEmployee = await createEmployee(payload);
      setEmployee(newEmployee);
      setLeaveForm((prev) => ({ ...prev, employeeId: newEmployee.employeeId }));
      alert("Employee created successfully!");
    } catch (err) {
      console.error(err);
      alert("Failed to create employee");
    } finally {
      setLoading(false);
    }
  };

  const handleLeaveSubmit = async (e) => {
    e.preventDefault();
    if (!leaveForm.employeeId) return alert("Employee ID not set!");

    try {
      const payload = {
        employeeId: leaveForm.employeeId,
        startDate: leaveForm.startDate,
        endDate: leaveForm.endDate,
        reason: leaveForm.reason,
      };
      await applyLeave(payload);
      alert("Leave applied successfully!");
      setLeaveForm({ ...leaveForm, startDate: "", endDate: "", reason: "" });
      fetchLeaves(employee.employeeId);
    } catch (err) {
      console.error("Error applying leave:", err.response?.data || err.message);
      alert("Failed to apply leave");
    }
  };

  return (
    <div style={{ minHeight: "100vh", background: "linear-gradient(to right, #f1f8ff, #e3f2fd)", padding: "20px" }}>
      <div className="container">
        <h1 className="text-center mb-4">Employee Dashboard</h1>

        {!employee ? (
          <div className="card p-4 shadow-sm mb-4">
            <h3 className="mb-3">Create Employee</h3>
            <form onSubmit={handleSubmit}>
              <div className="row g-3">
                {Object.keys(formData)
                  .filter((key) => key !== "userid")
                  .map((key) => (
                    <div className="col-md-6" key={key}>
                      <label className="form-label">{key.replace("_", " ").toUpperCase()}</label>
                      <input
                        type={key === "dob" ? "date" : key === "salary" ? "number" : "text"}
                        name={key}
                        value={formData[key]}
                        onChange={handleChange}
                        required
                        className="form-control"
                      />
                    </div>
                  ))}
              </div>
              <button type="submit" disabled={loading} className="btn btn-primary mt-3">
                {loading ? "Creating..." : "Create Employee"}
              </button>
            </form>
          </div>
        ) : (
          <>
            <div className="card p-3 shadow-sm mb-4">
              <h3>My Profile</h3>
              <div className="table-responsive">
                <table className="table table-striped">
                  <tbody>
                    {[
                      ["Employee ID", employee.employeeId],
                      ["First Name", employee.first_name],
                      ["Last Name", employee.last_name],
                      ["DOB", employee.dob],
                      ["Phone", employee.phone],
                      ["Address", employee.address],
                      ["Department", employee.department],
                      ["Designation", employee.designation],
                      ["Salary", employee.salary],
                    ].map(([label, value]) => (
                      <tr key={label}>
                        <th>{label}</th>
                        <td>{value}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>

            <div className="card p-3 shadow-sm mb-4">
              <h3>Apply Leave</h3>
              <form onSubmit={handleLeaveSubmit}>
                <div className="row g-3">
                  <div className="col-md-6">
                    <input
                      type="date"
                      name="startDate"
                      value={leaveForm.startDate}
                      onChange={handleLeaveChange}
                      className="form-control"
                      required
                    />
                  </div>
                  <div className="col-md-6">
                    <input
                      type="date"
                      name="endDate"
                      value={leaveForm.endDate}
                      onChange={handleLeaveChange}
                      className="form-control"
                      required
                    />
                  </div>
                  <div className="col-12">
                    <textarea
                      name="reason"
                      value={leaveForm.reason}
                      onChange={handleLeaveChange}
                      placeholder="Reason"
                      className="form-control"
                      required
                    />
                  </div>
                </div>
                <button type="submit" className="btn btn-success mt-3">Apply Leave</button>
              </form>
            </div>

            <div className="card p-3 shadow-sm">
              <h3>My Leave Requests</h3>
              {leaves.length === 0 ? (
                <p>No leave requests yet.</p>
              ) : (
                <div className="table-responsive">
                  <table className="table table-bordered table-hover">
                    <thead className="table-light">
                      <tr>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Reason</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      {leaves.map((leave, i) => (
                        <tr key={i}>
                          <td>{leave.startDate}</td>
                          <td>{leave.endDate}</td>
                          <td>{leave.reason}</td>
                          <td>{leave.status}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default EmployeeDashboard;
