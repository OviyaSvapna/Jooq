package com.example.JooqSpringBoot.service;

import com.example.JooqSpringBoot.entity.Customer;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    DSLContext jooqContext;

    public List<Customer> getCustomers(){
        return jooqContext
                .selectFrom(DSL.table("customers")).fetchInto(Customer.class);
    }

    public int insertCustomer(Customer customer) {

        List InsertColumns = List.of(
            DSL.field("first_name"),
                    DSL.field("last_name"),
                    DSL.field("email")
        );
        int status=jooqContext.insertInto(DSL.table("customers"), InsertColumns)
                .values(
                        customer.getFirstName(),customer.getLastName(),customer.getEmail()
                ).execute();
        return status;

    }

    public int updateCustomer(String firstName,String lastName,String email,int id) {

                int status=jooqContext.update(DSL.table("customers"))
                .set(DSL.field("first_name"),firstName)
                .set(DSL.field("last_name"), lastName)
                        .set(DSL.field("email"),email)
                .where(DSL.field("id").eq(id))
                .execute();

        return status;

    }

    public int deleteCustomer(int id)
    {

        int status = jooqContext.deleteFrom(DSL.table("customers")).where(DSL.field("id").eq(id)).execute();
        return status;




    }
}
