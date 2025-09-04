//package com.example.EmployeeService.EmployeeService;
//
//import com.example.EmployeeService.EmployeeService.client.AdminClient;
//import com.example.EmployeeService.EmployeeService.dto.EmployeeDto;
//import com.example.EmployeeService.EmployeeService.entity.Employee;
//import com.example.EmployeeService.EmployeeService.repository.EmployeeRepository;
//import com.example.EmployeeService.EmployeeService.service.EmployeeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EmployeeServiceTest {
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private AdminClient adminClient;
//
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateEmployee_Success() {
//        EmployeeDto dto = new EmployeeDto();
//        dto.setFirst_name("Nani");
//        dto.setLast_name("Kumar");
//        dto.setEmail("nani@gmail.com");
//        dto.setDob("11-06-2001");
//        dto.setPhone("9381804326");
//        dto.setAddress("Guntur");
//        dto.setDepartment("IT");
//        dto.setDesignation("Software Developer");
//        dto.setSalary(600000.0);
//        dto.setEmployeeId("Nani1122");
//
//        String jwtToken = "mocked-jwt-token";
//
//
//        when(adminClient.getUserIdByEmail(eq("nani@gmail.com"), eq("Bearer " + jwtToken)))
//                .thenReturn("USR-dd965040");
//
//
//        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Employee result = employeeService.createEmployee(dto, jwtToken);
//
//
//        assertNotNull(result);
//        assertEquals("Nani1122", result.getEmployeeId());
//        assertEquals("USR-dd965040", result.getUserid());
//
//        verify(adminClient, times(1)).getUserIdByEmail("nani@gmail.com", "Bearer " + jwtToken);
//        verify(employeeRepository, times(1)).save(any(Employee.class));
//    }
//
//
//    @Test
//    void testFindByEmployeeId_Found() {
//        Employee emp = new Employee();
//        emp.setEmployeeId("Nani1122");
//
//        when(employeeRepository.findByemployeeId("Nani1122")).thenReturn(Optional.of(emp));
//
//        Optional<Employee> result = employeeService.findByEmployeeId("Nani1122");
//
//        assertTrue(result.isPresent());
//        assertEquals("Nani1122", result.get().getEmployeeId());
//        verify(employeeRepository, times(1)).findByemployeeId("Nani1122");
//    }
//
//    @Test
//    void testFindByEmployeeId_NotFound() {
//        when(employeeRepository.findByemployeeId("Nani1122")).thenReturn(Optional.empty());
//
//        Optional<Employee> result = employeeService.findByEmployeeId("Nani1122");
//
//        assertFalse(result.isPresent());
//        verify(employeeRepository, times(1)).findByemployeeId("Nani1122");
//    }
//
//
//    @Test
//    void testDeleteEmployee_Success() {
//        Employee emp = new Employee();
//        emp.setEmployeeId("Nani1122");
//
//        when(employeeRepository.findByemployeeId("Nani1122")).thenReturn(Optional.of(emp));
//        doNothing().when(employeeRepository).delete(emp);
//
//        assertDoesNotThrow(() -> employeeService.deleteEmployee("Nani1122"));
//        verify(employeeRepository, times(1)).delete(emp);
//    }
//
//    @Test
//    void testDeleteEmployee_NotFound() {
//        when(employeeRepository.findByemployeeId("Nani1122")).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> employeeService.deleteEmployee("Nani1122"));
//
//        assertEquals("Employee not found with ID: Nani1122", exception.getMessage());
//        verify(employeeRepository, never()).delete(any(Employee.class));
//    }
//}
