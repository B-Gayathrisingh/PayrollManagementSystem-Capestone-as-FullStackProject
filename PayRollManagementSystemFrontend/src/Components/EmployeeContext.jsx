import React, { createContext, useState, useEffect } from "react";
import { getAllEmployees } from "./FetchApi";

export const EmployeeContext = createContext();

export const EmployeeProvider = ({ children }) => {
  const [employees, setEmployees] = useState([]);

  const fetchEmployees = async () => {
    try {
      const data = await getAllEmployees();
      setEmployees(data || []);
    } catch (err) {
      console.error("Error fetching employees:", err);
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  return (
    <EmployeeContext.Provider value={{ employees, setEmployees, fetchEmployees }}>
      {children}
    </EmployeeContext.Provider>
  );
};
