import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getEmployee } from "../api";

const EmployeeDetail = () => {
  const { employeeId } = useParams();
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    fetchEmployee();
  }, [employeeId]);

  const fetchEmployee = async () => {
    try {
      const data = await getEmployee(employeeId);
      setEmployee(data);
    } catch (err) {
      console.error("Failed to fetch employee:", err);
    }
  };

  if (!employee) {
    return <div className="text-center py-5">Loading employee details...</div>;
  }

  return (
    <div className="container py-5" style={{ maxWidth: "700px" }}>
      <h2 className="mb-4 text-center text-primary">Employee Details</h2>
      <div className="card p-4">
        <p><strong>Employee ID:</strong> {employee.employeeId}</p>
        <p><strong>Name:</strong> {employee.first_name} {employee.last_name}</p>
        <p><strong>Email:</strong> {employee.email}</p>
        <p><strong>DOB:</strong> {employee.dob}</p>
        <p><strong>Phone:</strong> {employee.phone}</p>
        <p><strong>Address:</strong> {employee.address}</p>
        <p><strong>Department:</strong> {employee.department}</p>
        <p><strong>Designation:</strong> {employee.designation}</p>
        <p><strong>Salary:</strong> {employee.salary}</p>
      </div>
    </div>
  );
};

export default EmployeeDetail;
