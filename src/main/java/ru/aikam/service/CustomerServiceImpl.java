package ru.aikam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aikam.dto.stat.output.CustomerStatDTO;
import ru.aikam.dto.stat.output.PurchaseDTO;
import ru.aikam.entity.Customer;
import ru.aikam.repository.CustomerRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseStartingWith(lastName);
    }

    @Override
    public List<Customer> findByGoodNameAndQuantity(String goodName, Integer quantity) {
        return customerRepository.findByByingGoodNotLessIntTimes(goodName, quantity);
    }

    @Override
    public List<Customer> findByMinMaxSpendedMoney(BigDecimal min, BigDecimal max) {
        return customerRepository.findBySpendedMoneyBetween(min, max);
    }

    @Override
    public List<Customer> findBadCustomers(Integer badCustomers) {
        return customerRepository.findBadCuctomers(badCustomers);
    }


    /**
     * Метод возвращает статистику в виде списка покупателей и названия товаров с потраченными на них деньгами
     */
    @Override
    public List<CustomerStatDTO> findStat(Date startDate, Date endDate) {
        List<CustomerStatDTO> listCustomerStat = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        for(Customer customer: customerList) {
            List<PurchaseDTO> purchaseList = new ArrayList<>();
            List<Object[]> objList = customerRepository.findStat(startDate, endDate, customer.getId());

            for (Object[] o : objList) {
                String name = (String) o[0];
                BigDecimal sum = (BigDecimal) o[1];
                purchaseList.add(new PurchaseDTO(name, sum));
            }

            //сортировка по убыванию потраченных денег за каждый продукт
            purchaseList.sort(new Comparator<PurchaseDTO>() {
                @Override
                public int compare(PurchaseDTO o1, PurchaseDTO o2) {
                    return o1.getExpenses().compareTo(o2.getExpenses()) * (-1);
                }
            });

            String customerName = customer.getFirstName() + " " + customer.getLastName();
            listCustomerStat.add(new CustomerStatDTO(customerName, purchaseList));
        }

        //Сортировка по убыванию потраченных денег на покупателя
        listCustomerStat.sort(new Comparator<CustomerStatDTO>() {
            @Override
            public int compare(CustomerStatDTO o1, CustomerStatDTO o2) {
                return o1.getTotalExpenses().compareTo(o2.getTotalExpenses()) * (-1);
            }
        });
        return listCustomerStat;
    }


}
