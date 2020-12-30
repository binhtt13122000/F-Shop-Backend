package com.dev.fshop.generator.entities;

import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Promotion;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class UserIdPrefixedSequencePromotionIdGenerator extends SequenceStyleGenerator {
    public static final String CODE_NUMBER_SEPARATOR_PARAMETER = "codeNumberSeparator";
    public static final String CODE_NUMBER_SEPARATOR_DEFAULT = "_";
    private String codeNumberSeparator;

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%04d";
    private String format;
    private String numberFormat;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        codeNumberSeparator = ConfigurationHelper.getString(CODE_NUMBER_SEPARATOR_PARAMETER,params,CODE_NUMBER_SEPARATOR_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,params,NUMBER_FORMAT_DEFAULT);
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER,params,VALUE_PREFIX_DEFAULT);
        this.format = "%1$s" + codeNumberSeparator + valuePrefix + numberFormat;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return String.format("%1$s", ((Promotion) object).getUserId())  + "_" + valuePrefix + String.format(numberFormat, super.generate(session, object));
    }
}
