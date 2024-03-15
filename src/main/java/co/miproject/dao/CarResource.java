package co.miproject.dao;
import co.miproject.entity.Car;
import io.quarkus.hibernate.orm.panache.*;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public interface CarResource extends PanacheEntityResource<Car, Long> {

}