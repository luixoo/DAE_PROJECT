package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.security.auth.Subject;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-27T16:41:34")
@StaticMetamodel(Teacher.class)
public class Teacher_ extends User_ {

    public static volatile ListAttribute<Teacher, Subject> subjects;
    public static volatile SingularAttribute<Teacher, String> office;

}