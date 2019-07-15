package info.javaknowledge.project36.daoimpl;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.BillingAddressService;
import info.javaknowledge.project36.model.BillingAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillingAddressImpl implements BillingAddressService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertBillingAddress(BillingAddress billingaddress) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.getTransaction();
        tr.begin();
        s.save(billingaddress);
        tr.commit();
        s.close();

        return null;
    }

    @Override
    public String updateBillingAddress(int addressid, BillingAddress billingaddress) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.getTransaction();
        tr.begin();
        BillingAddress bm = (BillingAddress) s.get(BillingAddress.class, addressid);

        bm.setAddressid(billingaddress.getAddressid());
        bm.setCity(billingaddress.getCity());
        bm.setCity(billingaddress.getDistrict());
        bm.setPhone(billingaddress.getPhone());
        bm.setEmailid(billingaddress.getEmailid());

        s.update(bm);

        tr.commit();
        s.close();
        return null;
    }

    @Override
    public String deleteBillingAddress(int addressid) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.getTransaction();
        tr.begin();
        BillingAddress bm = (BillingAddress) s.get(BillingAddress.class, addressid);
        s.delete(bm);
        tr.commit();
        s.close();
        return null;
    }

    @Override
    public String viewBillingAddresses(String emailid) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.getTransaction();
        tr.begin();
        ArrayList<BillingAddress> addressarray = new ArrayList<BillingAddress>();
        List<BillingAddress> addresslist = s.createQuery("from BillingAddress").list();
        Iterator<BillingAddress> iterator = addresslist.iterator();
        while (iterator.hasNext()) {
            BillingAddress bam = iterator.next();
            if (bam.getEmailid().equals(emailid)) {
                addressarray.add(bam);
            }
        }
        Gson gson = new Gson();
        String addressjsonlist = gson.toJson(addressarray);
        tr.commit();
        s.close();
        return addressjsonlist;
    }

    @Override
    public BillingAddress viewOneBillingAddress(int bid) {
        Session s = sessionFactory.openSession();
        Transaction tr = s.getTransaction();
        tr.begin();
        BillingAddress oneaddressobj = (BillingAddress) s.get(BillingAddress.class, bid);
        tr.commit();
        s.close();
        return oneaddressobj;
    }

}
