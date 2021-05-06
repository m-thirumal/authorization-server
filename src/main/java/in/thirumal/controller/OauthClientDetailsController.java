package in.thirumal.controller; 

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.repository.OauthClientDetailsRepository;
import lombok.RequiredArgsConstructor;
/**
 * @author Thirumal
 *
 */
@RestController@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class OauthClientDetailsController {

	//private final OauthClientDetailsRepository oauthClientDetailsRepository;
/*
	@GetMapping("")
	public Flux<OauthClientDetails> list() {
		return oauthClientDetailsRepository.findAll();
	}

	@GetMapping(value = "/{clientId}")
	public Mono<OauthClientDetails> getOne(@PathVariable String clientId) {
		return oauthClientDetailsRepository.findByClientId(clientId);
	}*/

}
