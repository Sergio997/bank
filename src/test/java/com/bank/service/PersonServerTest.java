//package com.bank.service;
//
//import com.bank.dto.request.PersonRequest;
//import com.bank.dto.response.PersonResponse;
//import com.bank.mapper.PersonMapper;
//import com.bank.model.Person;
//import com.bank.repo.PersonRepo;
//import com.bank.service.impl.PersonServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doNothing;
//
//
//@ExtendWith(SpringExtension.class)
//public class PersonServerTest {
//
//    @InjectMocks
//    private PersonServiceImpl testObj;
//
//    @Mock
//    private PersonRepo repo;
//    @Mock
//    private PersonMapper personMapper;
//
//    private Person person;
//    private PersonRequest personRequest;
//    private PersonResponse personResponse;
//
//    @BeforeEach()
//    public void setUp() {
//        person = new Person() {{
//            setId(1L);
//            setFirstName("John");
//            setSecondName("Gray");
//            setEmail("email");
//        }};
//
//        personResponse = new PersonResponse() {{
//            setFirstName("John");
//            setSecondName("Gray");
//            setEmail("email");
//        }};
//
//        personRequest = new PersonRequest() {{
//            setFirstName("John");
//            setSecondName("Gray");
//            setEmail("email");
//        }};
//    }
//
//    @Test
//    public void findById() {
//        Mockito.when(repo.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(person));
//
//        PersonResponse personActual = testObj.getPerson(1L);
//
//        Mockito.verify(this.repo)
//                .findById(1L);
//
//    }
//
//    @Test
//    public void findByIdWithException() {
//        assertThrows(NullPointerException.class, () -> {
//            testObj.getPerson(14L);
//        });
//    }
//
//    @Test
//    public void create() {
//        Mockito.when(personMapper.requestToEntity(personRequest))
//                .thenReturn(person);
//        Mockito.when(repo.save(person))
//                .thenReturn(person);
//        Mockito.when(personMapper.toDtoResponse(person))
//                .thenReturn(personResponse);
//
//        PersonResponse response = testObj.createPerson(personRequest);
//
//        Mockito.verify(personMapper)
//                .requestToEntity(personRequest);
//        Mockito.verify(repo)
//                .save(person);
//
//        assertThat(personResponse).isEqualTo(response);
//    }
//
//    @Test
//    public void update() {
//        Mockito.when(repo.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(person));
//        Mockito.when(personMapper.requestToEntity(personRequest, person))
//                .thenReturn(person);
//        Mockito.when(repo.save(person))
//                .thenReturn(person);
//        Mockito.when(personMapper.toDtoResponse(person))
//                .thenReturn(personResponse);
//
//        PersonResponse response = testObj.updatePerson(personRequest,1L);
//
//        Mockito.verify(repo)
//                .findById(1L);
//        Mockito.verify(personMapper)
//                .requestToEntity(personRequest, person);
//        Mockito.verify(repo)
//                .save(person);
//
//        assertThat(personResponse).isEqualTo(response);
//    }
//
//    @Test
//    public void updateWithException() {
//        Mockito.when(repo.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(person));
//        Mockito.when(personMapper.requestToEntity(personRequest, person))
//                .thenReturn(person);
//        Mockito.when(repo.save(person))
//                .thenReturn(person);
//        Mockito.when(personMapper.toDtoResponse(person))
//                .thenReturn(personResponse);
//
//        assertThrows(NullPointerException.class, () ->
//                testObj.updatePerson(personRequest,8L));
//    }
//
//    @Test
//    public void delete() {
//        Mockito.when(repo.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(person));
//        doNothing()
//                .when(repo)
//                .deleteById(1L);
//
//        testObj.deletePerson(1L);
//
//        Mockito.verify(repo)
//                .findById(1L);
//        Mockito.verify(repo)
//                .deleteById(1L);
//    }
//
//    @Test
//    public void deleteWithException() {
//        Mockito.when(repo.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(person));
//        doNothing()
//                .when(repo)
//                .deleteById(1L);
//
//        assertThrows(NullPointerException.class, () ->
//                testObj.deletePerson(2L));
//    }
//}
