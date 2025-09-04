import axios from "axios";


export const userApi = axios.create({
  baseURL: "http://localhost:8888" // User service
});

export const employeeApi = axios.create({
  baseURL: "http://localhost:8889", // Employee service
});

export const payrollApi = axios.create({
  baseURL: "http://localhost:8890" // Payroll service
});

export const leaveApi = axios.create({
  baseURL: "http://localhost:8891" // Leave service
});

// -------------------- HEADERS --------------------
export const getHeader = () => {
  const token = localStorage.getItem("token"); // JWT if needed
  return {
    "Content-Type": "application/json",
    Authorization: token ? `Bearer ${token}` : "",
  };
};

// -------------------- USER & ROLES --------------------

// Register user
export async function registerUser(user) {
  const response = await userApi.post("/api/auth/register", user);
  return response.data;
}

// Login
export async function loginUser(credentials) {
  const response = await userApi.post("/api/auth/login", credentials);
  return response.data;
}

// Get user profile
export async function getUserProfile(userId) {
  const response = await userApi.get(`/users/profile/${userId}`, {
    headers: getHeader()
  });
  return response.data;
}

// Get all roles
export async function getRoles() {
  const response = await userApi.get("/roles", { headers: getHeader() });
  return response.data;
}

// Add role
export async function addRole(role) {
  const response = await userApi.post("/roles", role, { headers: getHeader() });
  return response.data;
}

// Delete user
export async function deleteUser(userId) {
  const response = await userApi.delete(`/users/delete/${userId}`, { headers: getHeader() });
  return response.data;
}

// -------------------- EMPLOYEE --------------------

// Create employee
export async function createEmployee(employee) {
  const response = await employeeApi.post("/employee/saveemployee", employee, {
    headers: getHeader(),
  });
  return response.data;
}

// Get employee by employeeId
export async function getEmployee(employeeId) {
  const response = await employeeApi.get(`/employee/${employeeId}`, {
    headers: getHeader(),
  });
  return response.data;
}

export async function getAllEmployees() {
  const response = await employeeApi.get("/employee/allemployees", {
    headers: getHeader(),
  });
  return response.data;
}

// Update employee
export async function updateEmployee(employeeId, employee) {
  const response = await employeeApi.put(`/employee/${employeeId}`, employee, {
    headers: getHeader(),
  });
  return response.data;
}

// Delete employee
export async function deleteEmployee(employeeId) {
  const response = await employeeApi.delete(`delete/employee/${employeeId}`, {
    headers: getHeader()
  });
  return response.data;
}

// -------------------- PAYROLL --------------------

// Create new payroll run
export async function createPayrollRun(year, month) {
  const response = await payrollApi.post(`/payroll/runs?year=${year}&month=${month}`, null, {
    headers: getHeader()
  });
  return response.data;
}

// Process payroll run
export async function processPayrollRun(runId) {
  const response = await payrollApi.post(`/payroll/runs/${runId}/process`, null, {
    headers: getHeader()
  });
  return response.data;
}

// Lock payroll run
export async function lockPayrollRun(runId) {
  const response = await payrollApi.post(`/payroll/runs/${runId}/lock`, null, {
    headers: getHeader()
  });
  return response.data;
}

// Get payroll items for a run
export async function getpayrolls() {
  const response = await payrollApi.get(`/payroll/allpayrolls`, {
    headers: getHeader()
  });
  return response.data;
}

// Get own payroll (employee)
export async function getMyPayroll(year, month) {
  const response = await payrollApi.get(`/payroll/my/${year}/${month}`, { headers: getHeader() });
  return response.data;
}
export async function deletePayroll(id) {
  const response = await payrollApi.delete(`/payroll/${id}`, { headers: getHeader() });
  return response.data;
}

// -------------------- LEAVE REQUEST --------------------

// Apply leave
export async function applyLeave(leaveRequest) {
  try {
    console.log("API Request:", leaveRequest);
    const response = await leaveApi.post(
      `/leaverequest/apply`,
      leaveRequest,
      { headers: getHeader() }
    );
    return response.data;
  } catch (err) {
    console.error("API Error:", err.response?.data || err.message);
    throw err;
  }
}



// Approve/Reject leave (Admin)
export async function updateLeaveStatus(leaveId, status) {
  const response = await leaveApi.put(`/leave/${leaveId}?status=${status}`, null, { headers: getHeader() });
  return response.data;
}

// Delete leave request
export async function deleteLeave(leaveId) {
  const response = await leaveApi.delete(`/leave/${leaveId}`, { headers: getHeader() });
  return response.data;
}

// Get leave by ID
export async function getLeaveById(leaveId) {
  const response = await leaveApi.get(`/leave/${leaveId}`, { headers: getHeader() });
  return response.data;
}

// Get all leave requests (Admin)
export async function getAllLeaves() {
  const response = await leaveApi.get(`/leaveRequest/allleaves`, { headers: getHeader() });
  return response.data;
}

// Get all leaves for an employee
export async function getLeavesByEmployee(employeeId) {
  const response = await leaveApi.get(`/leave/user/${employeeId}`, { headers: getHeader() });
  return response.data;
}
