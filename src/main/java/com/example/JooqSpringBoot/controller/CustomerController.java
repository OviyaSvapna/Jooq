package com.example.JooqSpringBoot.controller;

import com.example.JooqSpringBoot.entity.Customer;
import com.example.JooqSpringBoot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/list")
    public List<Customer> getCustomers(){
        return this.customerService.getCustomers();
    }

    @PostMapping("/insert")
    public String postCustomer(@RequestBody Customer customer){
      int status=this.customerService.insertCustomer(customer);

      if(status>0)
          return "Values inserted Successfully";

      return "Values are not inserted";
    }

//    @GetMapping("/update/{id}/{firstName}/{lastName}/{email}")
//    public String updateCustomer(@PathVariable int id,@PathVariable String firstName,@PathVariable String lastName,@PathVariable String email){
//        customerService.updateCustomer(firstName,lastName,email,id);
//        return "Updated the customer successfully";
//    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable int id,@RequestBody Customer customer){
       int status= customerService.updateCustomer(customer.getFirstName(),customer.getLastName(),customer.getEmail(),id);

        if(status>0)
            return "Updated the customer successfully";

        return "Values are not updated";

    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id){

        int status=customerService.deleteCustomer(id);
        if(status>0)
            return "Deleted the customer successfully";

        return "Values are not deleted";

    }

}
