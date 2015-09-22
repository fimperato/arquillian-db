package it.imperato.test.services.ejb;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;


public class HibernateUtils {
  private HibernateUtils() {

  }

  private static Log logger = LogFactory.getLog(HibernateUtils.class);

  public static void recursiveInitialize(Object obj) {
    new RecursiveInitializer().initialize(obj);
  }

  private static class RecursiveInitializer {
    private HashSet<Object> checkedObjects = new HashSet<Object>();

    public void initialize(Object obj) {
      if (obj == null || obj.getClass().getCanonicalName() == null) {
        return;
      }
      if (checkedObjects.contains(obj)
          || !obj.getClass().getCanonicalName()
              .contains("it.imperato")) {
        return;
      }
      checkedObjects.add(obj);
      if (!Hibernate.isInitialized(obj)) {
        Hibernate.initialize(obj);
      }
      PropertyDescriptor[] properties = PropertyUtils
          .getPropertyDescriptors(obj);
      for (PropertyDescriptor propertyDescriptor : properties) {
        try {
          Object propertyObj = PropertyUtils.getProperty(obj,
              propertyDescriptor.getName());
          if (propertyObj != null) {
            initialize(propertyObj);
          }
          if (propertyObj instanceof Collection<?>) {
            for (Object item : (Collection<?>) propertyObj) {
              initialize(item);
            }
          }
        } catch (Exception e) {
          logger.warn("Ignore property : " + propertyDescriptor, e);
        }
      }
    }
  }
}
