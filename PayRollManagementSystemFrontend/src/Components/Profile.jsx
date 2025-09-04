// import React, { useState } from "react";
// import axios from "axios";

// const Profile = () => {
//   const user = JSON.parse(localStorage.getItem("user")); // logged-in user
//   const [employee, setEmployee] = useState(null); // store employee after creation
//   const [formData, setFormData] = useState({
//     employeeId: "",
//     first_name: "",
//     last_name: "",
//     dob: "",
//     phone: "",
//     address: "",
//     department: "",
//     designation: "",
//     salary: "",
//     email: "",
//     userid: user?.id || "",
//   });
//   const [loading, setLoading] = useState(false);
//   const [message, setMessage] = useState({ type: "", text: "" });

//   const getHeader = () => {
//     const token = localStorage.getItem("token");
//     return {
//       Authorization: token ? `Bearer ${token}` : "",
//       "Content-Type": "application/json",
//     };
//   };

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const handleSave = async () => {
//     setLoading(true);
//     setMessage({ type: "", text: "" });

//     try {
//       const response = await axios.post(
//         "http://localhost:8889/employee/saveemployee",
//         formData,
//         { headers: getHeader() }
//       );

//       setEmployee(response.data); // show logged-in employee
//       setMessage({ type: "success", text: "✅ Employee profile created!" });
//     } catch (error) {
//       console.error(error);
//       setMessage({
//         type: "danger",
//         text: error.response
//           ? `Error: ${error.response.status} - ${error.response.data}`
//           : "Network error. Please check server connection.",
//       });
//     }
//     setLoading(false);
//   };

//   return (
//     <div className="container py-5" style={{ maxWidth: "700px" }}>
//       <h2 className="text-center mb-4 text-primary">Employee Profile</h2>

//       {message.text && (
//         <div className={`alert alert-${message.type}`} role="alert">
//           {message.text}
//         </div>
//       )}

//       {/* Show form only if employee not created yet */}
//       {!employee && (
//         <div className="row g-3">
//           {Object.keys(formData)
//             .filter((key) => key !== "userid") // hide userid in form
//             .map((key) => (
//               <div className="col-md-6" key={key}>
//                 <label className="form-label">
//                   {key.replace("_", " ").toUpperCase()}
//                 </label>
//                 <input
//                   type={key === "dob" ? "date" : key === "salary" ? "number" : "text"}
//                   className="form-control"
//                   name={key}
//                   placeholder={`Enter ${key.replace("_", " ")}`}
//                   value={formData[key]}
//                   onChange={handleChange}
//                   required
//                 />
//               </div>
//             ))}
//           <div className="text-center mt-4">
//             <button
//               className="btn btn-primary px-4"
//               onClick={handleSave}
//               disabled={loading}
//             >
//               {loading ? "Saving..." : "Create Employee"}
//             </button>
//           </div>
//         </div>
//       )}

//       {/* Show profile after creation */}
//       {employee && (
//         <div className="mt-4">
//           <table className="table table-bordered table-striped">
//             <tbody>
//               {[
//                 ["Employee ID", employee.employeeId],
//                 ["First Name", employee.first_name],
//                 ["Last Name", employee.last_name],
//                 ["DOB", employee.dob],
//                 ["Phone", employee.phone],
//                 ["Address", employee.address],
//                 ["Department", employee.department],
//                 ["Designation", employee.designation],
//                 ["Salary", employee.salary],
//                 ["Email", employee.email],
//               ].map(([label, value]) => (
//                 <tr key={label}>
//                   <th style={{ width: "40%" }}>{label}</th>
//                   <td>{value}</td>
//                 </tr>
//               ))}
//             </tbody>
//           </table>
//         </div>
//       )}
//     </div>
//   );
// };

// export default Profile;
