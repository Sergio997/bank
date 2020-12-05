//package com.bank.mapper;
//
//import com.bank.dto.request.PersonRequest;
//import com.bank.dto.response.PersonResponse;
//import com.bank.model.Person;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//
//
//public class PersonMapperTest {
//    private final PersonMapper testObj = Mappers.getMapper(PersonMapper.class);
//
//    private Person person;
//    private PersonResponse personResponse;
//    private PersonRequest personRequest;
//
//    @BeforeEach()
//    public void setUp() {
//        person = new Person() {{
//            setId(1L);
//            setFirstName("John");
//            setSecondName("Gray");
////            setEmail("email");
//        }};
//
//        personResponse = new PersonResponse() {{
//            setFirstName("John");
//            setSecondName("Gray");
////            setEmail("email");
//        }};
//
//        personRequest = new PersonRequest() {{
//            setFirstName("John");
//            setSecondName("Gray");
////            setEmail("email");
//        }};
//    }
//
//    @Test
//    public void modelToResponse() {
//        PersonResponse personResponseActual = testObj.toDtoResponse(person);
//
//        Assertions.assertNotNull(personResponseActual);
//        Assertions.assertEquals(this.personResponse.getFirstName(), personResponseActual.getFirstName());
////        Assertions.assertEquals(this.personResponse.getEmail(), personResponseActual.getEmail());
//        Assertions.assertEquals(this.personResponse.getSecondName(), personResponseActual.getSecondName());
//    }
//
//    @Test
//    public void requestToModel() {
//        Person personActual = testObj.requestToEntity(personRequest);
//
//        Assertions.assertNotNull(personActual);
//        Assertions.assertEquals(person.getFirstName(), personActual.getFirstName());
////        Assertions.assertEquals(person.getEmail(), personActual.getEmail());
//        Assertions.assertEquals(person.getSecondName(), personActual.getSecondName());
//    }
//
//    @Test
//    public void requestToEntity() {
//        Person personActual = testObj.requestToEntity(personRequest, this.person);
//
//        Assertions.assertNotNull(personActual);
//        Assertions.assertEquals(person.getFirstName(), personActual.getFirstName());
////        Assertions.assertEquals(person.getEmail(), personActual.getEmail());
//        Assertions.assertEquals(person.getSecondName(), personActual.getSecondName());
//    }
//}
