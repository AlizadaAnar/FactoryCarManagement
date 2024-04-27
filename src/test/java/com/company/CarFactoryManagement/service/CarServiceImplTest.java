package com.company.CarFactoryManagement.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
//
//    @Mock
//    private AcademyRepository academyRepository;
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @InjectMocks
//    private StudentService studentService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreateStudent() {
//        // Mock data
//        StudentRequestDto requestDto = new StudentRequestDto();
//        requestDto.setName("John Doe");
//        requestDto.setAge(25);
//        requestDto.setAcademyId(1); // Assuming this is a valid academy ID as an int
//
//        // Mock Academy
//        Academy mockAcademy = new Academy();
//        mockAcademy.setId(1);
//        mockAcademy.setCourseName("Mock Academy");
//
//        // Mock behavior for academyRepository
//        when(academyRepository.findById(1)).thenReturn(Optional.of(mockAcademy));
//
//        // Call the service method
//        studentService.create(requestDto);
//
//        // Verify interactions
//        verify(academyRepository, times(1)).findById(1);
//        verify(studentRepository, times(1)).save(any(Student.class));
//
//        // You can add more assertions if needed
//    }
}
