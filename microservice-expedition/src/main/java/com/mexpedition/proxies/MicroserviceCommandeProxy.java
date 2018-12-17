package com.mexpedition.proxies;

 import com.mexpedition.beans.CommandeBean;
 import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface MicroserviceCommandeProxy {

    @GetMapping(value = "/microservice-commandes/commandes/{id}")
    CommandeBean recupererUneCommande(@PathVariable("id") int id);


}
