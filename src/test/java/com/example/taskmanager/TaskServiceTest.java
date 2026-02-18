package com.example.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    private User testUser;
    private Task testTask;

    @BeforeEach
    void setUp() {
        testUser = createUser("testuser");
        testUser.setId(1L);

        testTask = createTask("Test Task", "Test Description", false);
        testTask.setId(1L);
        testTask.setUser(testUser);
    }

    private User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    private Task createTask(String title, String description, boolean completed) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(completed);
        return task;
    }

    private MockedStatic<SecurityContextHolder> setupSecurityContext(String username) {
        MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = Mockito.mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
        return mockedSecurityContextHolder;
    }

    @Test
    void testFindUserTasks() {
        when(taskRepository.findByUserUsername("testuser")).thenReturn(Arrays.asList(testTask));

        try (var mocked = setupSecurityContext("testuser")) {
            List<Task> result = taskService.findUserTasks();
            assertEquals(Arrays.asList(testTask), result);
            verify(taskRepository).findByUserUsername("testuser");
        }
    }

    @Test
    void testSave() {
        Task newTask = createTask("New Task", "New Description", false);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(taskRepository.save(any(Task.class))).thenReturn(newTask);

        try (var mocked = setupSecurityContext("testuser")) {
            taskService.save(newTask);
            assertEquals(testUser, newTask.getUser());
            verify(userRepository).findByUsername("testuser");
            verify(taskRepository).save(newTask);
        }
    }

    @Test
    void testSave_UserNotFound() {
        Task newTask = createTask("New Task", "New Description", false);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        try (var mocked = setupSecurityContext("testuser")) {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.save(newTask));
            assertEquals("Usuario no encontrado", exception.getMessage());
        }
    }

    @Test
    void testObtainById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));

        try (var mocked = setupSecurityContext("testuser")) {
            Task result = taskService.obtainById(1L);
            assertEquals(testTask, result);
            verify(taskRepository).findById(1L);
        }
    }

    @Test
    void testObtainById_TaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        try (var mocked = setupSecurityContext("testuser")) {
            assertThrows(RuntimeException.class, () -> taskService.obtainById(1L));
        }
    }

    @Test
    void testObtainById_NoPermission() {
        User otherUser = createUser("otheruser");
        Task otherTask = createTask(null, null, false);
        otherTask.setUser(otherUser);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(otherTask));

        try (var mocked = setupSecurityContext("testuser")) {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.obtainById(1L));
            assertEquals("You don't have permission to access this task", exception.getMessage());
        }
    }

    @Test
    void testDelete() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));

        try (var mocked = setupSecurityContext("testuser")) {
            taskService.delete(1L);
            verify(taskRepository).delete(testTask);
        }
    }

    @Test
    void testUpdate() {
        Task taskDetails = createTask("Updated Title", "Updated Description", true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        try (var mocked = setupSecurityContext("testuser")) {
            taskService.update(1L, taskDetails);
            assertEquals("Updated Title", testTask.getTitle());
            assertEquals("Updated Description", testTask.getDescription());
            assertTrue(testTask.isCompleted());
            verify(taskRepository).save(testTask);
        }
    }

    @Test
    void testUpdate_BlankTitle() {
        Task taskDetails = createTask("   ", "Updated Description", true);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        try (var mocked = setupSecurityContext("testuser")) {
            taskService.update(1L, taskDetails);
            assertEquals("Test Task", testTask.getTitle());
            assertEquals("Updated Description", testTask.getDescription());
            assertTrue(testTask.isCompleted());
        }
    }

    @Test
    void testCheckCompleted() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        try (var mocked = setupSecurityContext("testuser")) {
            taskService.checkCompleted(1L);
            assertTrue(testTask.isCompleted());
            verify(taskRepository).save(testTask);
        }
    }
}
