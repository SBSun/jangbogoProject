package backend.jangbogoProject.config;

import backend.jangbogoProject.item.domain.Item;
import backend.jangbogoProject.item.repository.ItemRepository;
import backend.jangbogoProject.item.service.ItemService;
import backend.jangbogoProject.repository.*;
import backend.jangbogoProject.service.MemberService;
import backend.jangbogoProject.repository.MemberRepository;
import backend.jangbogoProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(new MemoryMemberRepository());
    }

   // @Bean
   // public MemberRepository memberRepository(){

   //     return new JpaMemberRepository(em);
   // }
}
