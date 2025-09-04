import { useState } from "react";

const LeaveRequest = () => {
  const [formData, setFormData] = useState({
    startDate: "",
    endDate: "",
    reason: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("Leave request submitted (UI only, no backend).");
    setFormData({ startDate: "", endDate: "", reason: "" });
  };

  return (
    <div className="card shadow-sm p-4">
      <h4 className="mb-3">Leave Request</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Start Date</label>
          <input
            type="date"
            className="form-control"
            name="startDate"
            value={formData.startDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">End Date</label>
          <input
            type="date"
            className="form-control"
            name="endDate"
            value={formData.endDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Reason</label>
          <textarea
            className="form-control"
            name="reason"
            rows="3"
            value={formData.reason}
            onChange={handleChange}
            required
          ></textarea>
        </div>

        <button className="btn btn-primary w-100">Submit Request</button>
      </form>
    </div>
  );
};

export default LeaveRequest;
