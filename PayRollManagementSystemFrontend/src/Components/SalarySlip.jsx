import React, { useEffect, useState } from "react";
import axios from "axios";

const SalarySlip = () => {
  const [salarySlip, setSalarySlip] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSalarySlip = async () => {
      try {
        const token = localStorage.getItem("token"); 
        const response = await axios.get(
          "http://localhost:8889/employee/salaryslip",
          { headers: { Authorization: `Bearer ${token}` } }
        );

        console.log("Salary API response:", response.data); // ✅ Check structure
        setSalarySlip(response.data);
      } catch (error) {
        console.error("Error fetching salary slip:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchSalarySlip();
  }, []);

  if (loading) return <p>Loading salary slip...</p>;
  if (!salarySlip) return <p>No salary slip found.</p>;

  // Use optional chaining and defaults to avoid errors
  const {
    employeeName = "",
    employeeId = "",
    department = "",
    designation = "",
    joiningDate = "",
    salaryMonth = "",
    earnings = [],
    deductions = []
  } = salarySlip;

  const totalEarnings = earnings.reduce((sum, item) => sum + (item.amount || 0), 0);
  const totalDeductions = deductions.reduce((sum, item) => sum + (item.amount || 0), 0);
  const netSalary = totalEarnings - totalDeductions;

  return (
    <div className="card shadow-sm p-4">
      <h4 className="mb-4 text-center">Salary Slip - {salaryMonth}</h4>

      <div className="row mb-4">
        <div className="col-md-6">
          <p><strong>Name:</strong> {employeeName}</p>
          <p><strong>Employee ID:</strong> {employeeId}</p>
          <p><strong>Department:</strong> {department}</p>
        </div>
        <div className="col-md-6 text-md-end">
          <p><strong>Designation:</strong> {designation}</p>
          <p><strong>Joining Date:</strong> {joiningDate}</p>
          <p><strong>Salary Month:</strong> {salaryMonth}</p>
        </div>
      </div>

      <table className="table table-bordered">
        <thead className="table-light">
          <tr>
            <th>Earnings</th>
            <th>Amount (₹)</th>
            <th>Deductions</th>
            <th>Amount (₹)</th>
          </tr>
        </thead>
        <tbody>
          {Math.max(earnings.length, deductions.length) > 0 &&
            Array.from({ length: Math.max(earnings.length, deductions.length) }).map((_, idx) => (
              <tr key={idx}>
                <td>{earnings[idx]?.title || ""}</td>
                <td>{earnings[idx]?.amount || 0}</td>
                <td>{deductions[idx]?.title || ""}</td>
                <td>{deductions[idx]?.amount || 0}</td>
              </tr>
            ))
          }
        </tbody>
        <tfoot>
          <tr className="table-light">
            <td><strong>Total Earnings</strong></td>
            <td><strong>{totalEarnings}</strong></td>
            <td><strong>Total Deductions</strong></td>
            <td><strong>{totalDeductions}</strong></td>
          </tr>
        </tfoot>
      </table>

      <div className="text-end mt-3">
        <h5>Net Salary: ₹{netSalary}</h5>
      </div>
    </div>
  );
};

export default SalarySlip;
