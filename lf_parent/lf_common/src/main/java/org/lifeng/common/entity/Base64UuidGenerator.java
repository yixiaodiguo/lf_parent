package org.lifeng.common.entity;
 
import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.lifeng.common.util.UuidUtils;
 
public class Base64UuidGenerator implements IdentifierGenerator {
 
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return UuidUtils.compressedUuid();
    }
 
}