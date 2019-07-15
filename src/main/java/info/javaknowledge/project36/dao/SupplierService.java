/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.dao;

import info.javaknowledge.project36.model.Supplier;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public interface SupplierService {
    public String insertSupplier(Supplier sm);

    public String updateSupplier(Supplier sm);

    public String deleteSupplier(String id);

    public String viewSupplier();

    public Supplier viewOneSupplier(String id);
}
