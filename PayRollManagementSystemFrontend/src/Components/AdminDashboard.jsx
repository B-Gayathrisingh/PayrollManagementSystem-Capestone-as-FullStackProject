import React, { useState, useEffect } from "react";
import {
  getAllEmployees,
  updateEmployee,
  deleteEmployee,
  getpayrolls,
  getAllLeaves,
  updateLeaveStatus,
  deletePayroll,
} from "./FetchApi";

const AdminDashboard = () => {
  const [employees, setEmployees] = useState([]);
  const [payrolls, setPayrolls] = useState([]);
  const [leaveRequests, setLeaveRequests] = useState([]);
  const [editingEmployeeId, setEditingEmployeeId] = useState(null);
  const [editingPayrollId, setEditingPayrollId] = useState(null);
  const [editEmployeeData, setEditEmployeeData] = useState({});
  const [editPayrollData, setEditPayrollData] = useState({});

  // Fetch all data
  const fetchData = async () => {
    try {
      const [empData, payrollData, leaveData] = await Promise.all([
        getAllEmployees(),
        getpayrolls(),
        getAllLeaves(),
      ]);
      setEmployees(empData || []);
      setPayrolls(payrollData || []);
      setLeaveRequests(leaveData || []);
    } catch (err) {
      console.error(err);
      alert("Failed to fetch data");
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  // Employee handlers
  const handleEmployeeEdit = (emp) => {
    setEditingEmployeeId(emp.employeeId);
    setEditEmployeeData({ ...emp });
  };

  const handleEmployeeUpdate = async (employeeId) => {
    try {
      const updated = await updateEmployee(employeeId, editEmployeeData);
      setEmployees(employees.map((e) => (e.employeeId === employeeId ? updated : e)));
      setEditingEmployeeId(null);
      alert("Employee updated successfully!");
    } catch (err) {
      console.error(err);
      alert("Failed to update employee");
    }
  };

  const handleEmployeeDelete = async (employeeId) => {
    if (!window.confirm("Are you sure?")) return;
    try {
      await deleteEmployee(employeeId);
      setEmployees(employees.filter((e) => e.employeeId !== employeeId));
      alert("Deleted successfully");
    } catch (err) {
      console.error(err);
      alert("Failed to delete employee");
    }
  };

  // Leave handlers
  const handleLeaveAction = async (leaveId, status) => {
    try {
      await updateLeaveStatus(leaveId, status);
      setLeaveRequests(
        leaveRequests.map((l) => (l.id === leaveId ? { ...l, status } : l))
      );
      alert(`Leave ${status.toLowerCase()} successfully!`);
    } catch (err) {
      console.error(err);
      alert("Failed to update leave status");
    }
  };

  // Payroll handlers
  const handlePayrollEdit = (pay) => {
    setEditingPayrollId(pay.id);
    setEditPayrollData({ ...pay });
  };

  const handlePayrollUpdate = async (payId) => {
    try {
      setEditingPayrollId(null);
      alert("Payroll updated successfully!");
    } catch (err) {
      console.error(err);
      alert("Failed to update payroll");
    }
  };

  const handlePayrollDelete = async (payId) => {
    if (!window.confirm("Are you sure?")) return;
    try {
      await deletePayroll(payId);
      setPayrolls(payrolls.filter((p) => p.id !== payId));
      alert("Payroll deleted successfully!");
    } catch (err) {
      console.error(err);
      alert("Failed to delete payroll");
    }
  };

  return (
    <div className="container-fluid py-4" style={{ background: "linear-gradient(135deg, #f9f9f9, #dfe9f3)", minHeight: "100vh" }}>
      <div className="container bg-white shadow rounded p-4">
        <h1 className="text-center mb-4 text-primary">Admin Dashboard</h1>

        {/* Employees Table */}
        <h3 className="text-secondary">Employees</h3>
        <div className="table-responsive mb-5">
          <table className="table table-bordered table-hover">
            <thead className="table-dark">
              <tr>
                <th>Emp ID</th>
                <th>First</th>
                <th>Last</th>
                <th>DOB</th>
                <th>Phone</th>
                <th>Dept</th>
                <th>Designation</th>
                <th>Salary</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {employees.map((emp) => (
                <tr key={emp.employeeId}>
                  {editingEmployeeId === emp.employeeId ? (
                    <>
                      <td><input className="form-control" value={editEmployeeData.employeeId} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, employeeId: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.first_name} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, first_name: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.last_name} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, last_name: e.target.value })} /></td>
                      <td><input className="form-control" type="date" value={editEmployeeData.dob} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, dob: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.phone} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, phone: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.department} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, department: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.designation} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, designation: e.target.value })} /></td>
                      <td><input className="form-control" type="number" value={editEmployeeData.salary} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, salary: e.target.value })} /></td>
                      <td><input className="form-control" value={editEmployeeData.email} onChange={(e) => setEditEmployeeData({ ...editEmployeeData, email: e.target.value })} /></td>
                      <td>
                        <button className="btn btn-success btn-sm me-2" onClick={() => handleEmployeeUpdate(emp.employeeId)}>Save</button>
                        <button className="btn btn-secondary btn-sm" onClick={() => setEditingEmployeeId(null)}>Cancel</button>
                      </td>
                    </>
                  ) : (
                    <>
                      <td>{emp.employeeId}</td>
                      <td>{emp.first_name}</td>
                      <td>{emp.last_name}</td>
                      <td>{emp.dob}</td>
                      <td>{emp.phone}</td>
                      <td>{emp.department}</td>
                      <td>{emp.designation}</td>
                      <td>{emp.salary}</td>
                      <td>{emp.email}</td>
                      <td>
                        <button className="btn btn-warning btn-sm me-2" onClick={() => handleEmployeeEdit(emp)}>Edit</button>
                        <button className="btn btn-danger btn-sm" onClick={() => handleEmployeeDelete(emp.employeeId)}>Delete</button>
                      </td>
                    </>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Leave Requests Table */}
        <h3 className="text-secondary">Leave Requests</h3>
        <div className="table-responsive mb-5">
          <table className="table table-bordered table-hover">
            <thead className="table-dark">
              <tr>
                <th>Emp ID</th>
                <th>Start</th>
                <th>End</th>
                <th>Type</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {leaveRequests.map((lr) => (
                <tr key={lr.id}>
                  <td>{lr.employeeId}</td>
                  <td>{lr.startDate}</td>
                  <td>{lr.endDate}</td>
                  <td>{lr.leaveType}</td>
                  <td>{lr.status}</td>
                  <td>
                    <button className="btn btn-success btn-sm me-2" onClick={() => handleLeaveAction(lr.id, "APPROVED")} disabled={lr.status !== "PENDING"}>Approve</button>
                    <button className="btn btn-danger btn-sm" onClick={() => handleLeaveAction(lr.id, "REJECTED")} disabled={lr.status !== "PENDING"}>Reject</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Payroll Table */}
        <h3 className="text-secondary">Payrolls</h3>
        <div className="table-responsive">
          <table className="table table-bordered table-hover">
            <thead className="table-dark">
              <tr>
                <th>Emp ID</th>
                <th>Basic</th>
                <th>Deductions</th>
                <th>Net</th>
                <th>Date</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {payrolls.map((p) => (
                <tr key={p.id}>
                  {editingPayrollId === p.id ? (
                    <>
                      <td><input className="form-control" value={editPayrollData.employeeId} onChange={(e) => setEditPayrollData({ ...editPayrollData, employeeId: e.target.value })} /></td>
                      <td><input className="form-control" type="number" value={editPayrollData.basicSalary} onChange={(e) => setEditPayrollData({ ...editPayrollData, basicSalary: e.target.value })} /></td>
                      <td><input className="form-control" type="number" value={editPayrollData.deductions} onChange={(e) => setEditPayrollData({ ...editPayrollData, deductions: e.target.value })} /></td>
                      <td><input className="form-control" type="number" value={editPayrollData.netSalary} onChange={(e) => setEditPayrollData({ ...editPayrollData, netSalary: e.target.value })} /></td>
                      <td><input className="form-control" type="date" value={editPayrollData.payrollDate} onChange={(e) => setEditPayrollData({ ...editPayrollData, payrollDate: e.target.value })} /></td>
                      <td>
                        <button className="btn btn-success btn-sm me-2" onClick={() => handlePayrollUpdate(p.id)}>Save</button>
                        <button className="btn btn-secondary btn-sm" onClick={() => setEditingPayrollId(null)}>Cancel</button>
                      </td>
                    </>
                  ) : (
                    <>
                      <td>{p.employeeId}</td>
                      <td>{p.basicSalary}</td>
                      <td>{p.deductions}</td>
                      <td>{p.netSalary}</td>
                      <td>{p.payrollDate}</td>
                      <td>
                        <button className="btn btn-warning btn-sm me-2" onClick={() => handlePayrollEdit(p)}>Edit</button>
                        <button className="btn btn-danger btn-sm" onClick={() => handlePayrollDelete(p.id)}>Delete</button>
                      </td>
                    </>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
