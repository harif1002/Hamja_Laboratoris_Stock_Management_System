/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.dao;

import info.javaknowledge.project36.model.BillingAddress;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public interface BillingAddressService {
     public String insertBillingAddress(BillingAddress billingaddress);

    public String updateBillingAddress(int addressid, BillingAddress billingaddress);

    public String deleteBillingAddress(int addressid);

    public String viewBillingAddresses(String emailid);

    public BillingAddress viewOneBillingAddress(int bid);

}
