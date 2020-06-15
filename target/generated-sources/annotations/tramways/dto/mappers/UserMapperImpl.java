package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.inbound.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-08T22:53:39+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class UserMapperImpl extends UserMapper {

    @Override
    public User map(tramways.core.model.persistable.users.User u) {
        if ( u == null ) {
            return null;
        }

        User user = new User();

        user.setUuid( u.getUuid() );
        user.setUsername( u.getUsername() );

        user.setRoles( toApi(u.listRoles()) );
        user.setResourceType( "User" );

        return user;
    }

    @Override
    public List<User> map(List<tramways.core.model.persistable.users.User> users) {
        if ( users == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( users.size() );
        for ( tramways.core.model.persistable.users.User user : users ) {
            list.add( map( user ) );
        }

        return list;
    }
}
