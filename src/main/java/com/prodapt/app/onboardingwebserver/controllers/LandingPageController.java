package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.app.onboardingwebserver.entities.ContactSpoc;
import com.prodapt.app.onboardingwebserver.entities.CandidateCategory;
import com.prodapt.app.onboardingwebserver.entities.FAQ;
import com.prodapt.app.onboardingwebserver.entities.HomeDoc;
import com.prodapt.app.onboardingwebserver.entities.LandingPage;
import com.prodapt.app.onboardingwebserver.entities.Link;
import com.prodapt.app.onboardingwebserver.repository.LandingPageRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/landing-page")
public class LandingPageController {

	@Autowired
	private LandingPageRepository landingPageRepository;

	@PostMapping("/save")
	public ResponseEntity<LandingPage> save(@Valid @RequestBody LandingPage landingPage) {
		return ResponseEntity.ok(landingPageRepository.save(landingPage));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<LandingPage> update(@PathVariable("id") String id,
			@Valid @RequestBody LandingPage landingPage) {
		if (landingPageRepository.existsById(id)) {
			landingPage.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<LandingPage> delete(@PathVariable("id") String id) {
		if (landingPageRepository.existsById(id)) {
			landingPageRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<LandingPage>> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(landingPageRepository.findById(id));
	}

	@GetMapping("/get/all")
	public ResponseEntity<LandingPage> getAll() {
		return ResponseEntity.ok(((List<LandingPage>) landingPageRepository.findAll()).get(0));
	}

	@PostMapping("/{id}/save-hrbp")
	public ResponseEntity<LandingPage> saveHrbp(@PathVariable("id") String id, @Valid @RequestBody ContactSpoc hrbp) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> hrbpspocList = new ArrayList<ContactSpoc>();
			if (landingPage.getHrbpSpocList() == null) {
				hrbpspocList.add(hrbp);
			} else {
				hrbpspocList.addAll(landingPage.getHrbpSpocList());
				hrbpspocList.add(hrbp);
			}
			landingPage.setHrbpSpocList(hrbpspocList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-hrbp/{hrbpId}")
	public ResponseEntity<ContactSpoc> getHrbp(@PathVariable("id") String id, @PathVariable("hrbpId") String hrbpId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> hrbpSpocList = landingPage.getHrbpSpocList();
			if (hrbpSpocList != null) {
				for (int i = 0; i < hrbpSpocList.size(); i++) {
					if (hrbpSpocList.get(i).getId().equals(hrbpId)) {
						return ResponseEntity.ok(hrbpSpocList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-hrbp/{hrbpId}")
	public ResponseEntity<LandingPage> updateHrbp(@PathVariable("id") String id, @PathVariable("hrbpId") String hrbpId,
			@Valid @RequestBody ContactSpoc hrbp) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> hrbpspocList = landingPage.getHrbpSpocList();
			if (hrbpspocList != null) {
				for (int i = 0; i < hrbpspocList.size(); i++) {
					if (hrbpspocList.get(i).getId().equals(hrbpId)) {
						hrbpspocList.set(i, hrbp);
						break;
					}
				}
				landingPage.setHrbpSpocList(hrbpspocList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-hrbp/{hrbpId}")
	public ResponseEntity<LandingPage> deletehrbp(@PathVariable("id") String id,
			@PathVariable("hrbpId") String hrbpId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> hrbpspocList = landingPage.getHrbpSpocList();
			if (hrbpspocList != null) {
				for (int i = 0; i < hrbpspocList.size(); i++) {
					if (hrbpspocList.get(i).getId().equals(hrbpId)) {
						hrbpspocList.remove(i);
						break;
					}
				}
				landingPage.setHrbpSpocList(hrbpspocList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-pro")
	public ResponseEntity<LandingPage> savePro(@PathVariable("id") String id, @Valid @RequestBody ContactSpoc pro) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> proboardingAdvisorList = new ArrayList<ContactSpoc>();
			if (landingPage.getProboardingAdvisorList() == null) {
				proboardingAdvisorList.add(pro);
			} else {
				proboardingAdvisorList.addAll(landingPage.getProboardingAdvisorList());
				proboardingAdvisorList.add(pro);
			}
			landingPage.setProboardingAdvisorList(proboardingAdvisorList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-pro/{proId}")
	public ResponseEntity<ContactSpoc> getPro(@PathVariable("id") String id, @PathVariable("proId") String proId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> proboardingAdvisorList = landingPage.getProboardingAdvisorList();
			if (proboardingAdvisorList != null) {
				for (int i = 0; i < proboardingAdvisorList.size(); i++) {
					if (proboardingAdvisorList.get(i).getId().equals(proId)) {
						return ResponseEntity.ok(proboardingAdvisorList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-pro/{proId}")
	public ResponseEntity<LandingPage> updatePro(@PathVariable("id") String id, @PathVariable("proId") String proId,
			@Valid @RequestBody ContactSpoc pro) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> proboardingAdvisorList = landingPage.getProboardingAdvisorList();
			if (proboardingAdvisorList != null) {
				for (int i = 0; i < proboardingAdvisorList.size(); i++) {
					if (proboardingAdvisorList.get(i).getId().equals(proId)) {
						proboardingAdvisorList.set(i, pro);
						break;
					}
				}
				landingPage.setProboardingAdvisorList(proboardingAdvisorList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-pro/{proId}")
	public ResponseEntity<LandingPage> deletepro(@PathVariable("id") String id, @PathVariable("proId") String proId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> proboardingAdvisorList = landingPage.getProboardingAdvisorList();
			if (proboardingAdvisorList != null) {
				for (int i = 0; i < proboardingAdvisorList.size(); i++) {
					if (proboardingAdvisorList.get(i).getId().equals(proId)) {
						proboardingAdvisorList.remove(i);
						break;
					}
				}
				landingPage.setProboardingAdvisorList(proboardingAdvisorList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PostMapping("/{id}/save-buddy")
	public ResponseEntity<LandingPage> saveBuddy(@PathVariable("id") String id, @Valid @RequestBody ContactSpoc buddy) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> buddyList = new ArrayList<ContactSpoc>();
			if (landingPage.getBuddyList() == null) {
				buddyList.add(buddy);
			} else {
				buddyList.addAll(landingPage.getBuddyList());
				buddyList.add(buddy);
			}
			landingPage.setBuddyList(buddyList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-buddy/{buddyId}")
	public ResponseEntity<ContactSpoc> getBuddy(@PathVariable("id") String id, @PathVariable("buddyId") String buddyId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> buddyList = landingPage.getBuddyList();
			if (buddyList != null) {
				for (int i = 0; i < buddyList.size(); i++) {
					if (buddyList.get(i).getId().equals(buddyId)) {
						return ResponseEntity.ok(buddyList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-buddy/{buddyId}")
	public ResponseEntity<LandingPage> updateBuddy(@PathVariable("id") String id, @PathVariable("buddyId") String buddyId,
			@Valid @RequestBody ContactSpoc buddy) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> buddyList = landingPage.getBuddyList();
			if (buddyList != null) {
				for (int i = 0; i < buddyList.size(); i++) {
					if (buddyList.get(i).getId().equals(buddyId)) {
						buddyList.set(i, buddy);
						break;
					}
				}
				landingPage.setBuddyList(buddyList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-buddy/{buddyId}")
	public ResponseEntity<LandingPage> deleteBuddy(@PathVariable("id") String id, @PathVariable("buddyId") String buddyId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<ContactSpoc> buddyList = landingPage.getBuddyList();
			if (buddyList != null) {
				for (int i = 0; i < buddyList.size(); i++) {
					if (buddyList.get(i).getId().equals(buddyId)) {
						buddyList.remove(i);
						break;
					}
				}
				landingPage.setBuddyList(buddyList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-faq")
	public ResponseEntity<LandingPage> saveFaq(@PathVariable("id") String id, @Valid @RequestBody FAQ faq) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<FAQ> faqList = new ArrayList<FAQ>();
			if (landingPage.getFaqList() == null) {
				faqList.add(faq);
			} else {
				faqList.addAll(landingPage.getFaqList());
				faqList.add(faq);
			}
			landingPage.setFaqList(faqList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-home-doc")
	public ResponseEntity<LandingPage> saveHomeDoc(@PathVariable("id") String id, @Valid @RequestBody HomeDoc homeDoc) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<HomeDoc> homeDocList = new ArrayList<HomeDoc>();
			if (landingPage.getHomeDocList() == null) {
				homeDocList.add(homeDoc);
			} else {
				homeDocList.addAll(landingPage.getHomeDocList());
				homeDocList.add(homeDoc);
			}
			landingPage.setHomeDocList(homeDocList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-faq/{faqId}")
	public ResponseEntity<FAQ> getFaq(@PathVariable("id") String id, @PathVariable("faqId") String faqId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<FAQ> faqList = landingPage.getFaqList();
			if (faqList != null) {
				for (int i = 0; i < faqList.size(); i++) {
					if (faqList.get(i).getId().equals(faqId)) {
						return ResponseEntity.ok(faqList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-home-doc/{homeDocId}")
	public ResponseEntity<HomeDoc> getHomeDoc(@PathVariable("id") String id, @PathVariable("homeDocId") String homeDocId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<HomeDoc> homeDocList = landingPage.getHomeDocList();
			if (homeDocList != null) {
				for (int i = 0; i < homeDocList.size(); i++) {
					if (homeDocList.get(i).getId().equals(homeDocId)) {
						return ResponseEntity.ok(homeDocList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PutMapping("/{id}/update-faq/{faqId}")
	public ResponseEntity<LandingPage> updateFaq(@PathVariable("id") String id, @PathVariable("faqId") String faqId,
			@Valid @RequestBody FAQ it) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<FAQ> faqList = landingPage.getFaqList();
			if (faqList != null) {
				for (int i = 0; i < faqList.size(); i++) {
					if (faqList.get(i).getId().equals(faqId)) {
						faqList.set(i, it);
						break;
					}
				}
				landingPage.setFaqList(faqList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-home-doc/{homeDocId}")
	public ResponseEntity<LandingPage> updateHomeDoc(@PathVariable("id") String id, @PathVariable("homeDocId") String homeDocId,
			@Valid @RequestBody HomeDoc homeDoc) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<HomeDoc> homeDocList = landingPage.getHomeDocList();
			if (homeDocList != null) {
				for (int i = 0; i < homeDocList.size(); i++) {
					if (homeDocList.get(i).getId().equals(homeDocId)) {
						homeDocList.set(i, homeDoc);
						break;
					}
				}
				landingPage.setHomeDocList(homeDocList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-faq/{faqId}")
	public ResponseEntity<LandingPage> deleteFaq(@PathVariable("id") String id, @PathVariable("faqId") String faqId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<FAQ> faqList = landingPage.getFaqList();
			if (faqList != null) {
				for (int i = 0; i < faqList.size(); i++) {
					if (faqList.get(i).getId().equals(faqId)) {
						faqList.remove(i);
						break;
					}
				}
				landingPage.setFaqList(faqList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-home-doc/{homeDocId}")
	public ResponseEntity<LandingPage> deleteHomeDoc(@PathVariable("id") String id, @PathVariable("homeDocId") String homeDocId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<HomeDoc> homeDocList = landingPage.getHomeDocList();
			if (homeDocList != null) {
				for (int i = 0; i < homeDocList.size(); i++) {
					if (homeDocList.get(i).getId().equals(homeDocId)) {
						homeDocList.remove(i);
						break;
					}
				}
				landingPage.setHomeDocList(homeDocList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-link")
	public ResponseEntity<LandingPage> saveLink(@PathVariable("id") String id, @Valid @RequestBody Link link) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = new ArrayList<Link>();
			if (landingPage.getLinkList() == null) {
				linkList.add(link);
			} else {
				linkList.addAll(landingPage.getLinkList());
				linkList.add(link);
			}
			landingPage.setLinkList(linkList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-link/{linkId}")
	public ResponseEntity<Link> getLink(@PathVariable("id") String id, @PathVariable("linkId") String linkId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = landingPage.getLinkList();
			if (linkList != null) {
				for (int i = 0; i < linkList.size(); i++) {
					if (linkList.get(i).getId().equals(linkId)) {
						return ResponseEntity.ok(linkList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-link/{linkId}")
	public ResponseEntity<LandingPage> updateLink(@PathVariable("id") String id, @PathVariable("linkId") String linkId,
			@Valid @RequestBody Link link) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = landingPage.getLinkList();
			if (linkList != null) {
				for (int i = 0; i < linkList.size(); i++) {
					if (linkList.get(i).getId().equals(linkId)) {
						linkList.set(i, link);
						break;
					}
				}
				landingPage.setLinkList(linkList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-link/{linkId}")
	public ResponseEntity<LandingPage> deleteLink(@PathVariable("id") String id,
			@PathVariable("linkId") String linkId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> faqList = landingPage.getLinkList();
			if (faqList != null) {
				for (int i = 0; i < faqList.size(); i++) {
					if (faqList.get(i).getId().equals(linkId)) {
						faqList.remove(i);
						break;
					}
				}
				landingPage.setLinkList(faqList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-youtube-link")
	public ResponseEntity<LandingPage> saveYoutubeLink(@PathVariable("id") String id, @Valid @RequestBody Link link) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = new ArrayList<Link>();
			if (landingPage.getYoutubeLinkList() == null) {
				linkList.add(link);
			} else {
				linkList.addAll(landingPage.getYoutubeLinkList());
				linkList.add(link);
			}
			landingPage.setYoutubeLinkList(linkList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-youtube-link/{linkId}")
	public ResponseEntity<Link> getYoutubeLink(@PathVariable("id") String id, @PathVariable("linkId") String linkId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = landingPage.getYoutubeLinkList();
			if (linkList != null) {
				for (int i = 0; i < linkList.size(); i++) {
					if (linkList.get(i).getId().equals(linkId)) {
						return ResponseEntity.ok(linkList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-youtube-link/{linkId}")
	public ResponseEntity<LandingPage> updateYoutubeLink(@PathVariable("id") String id,
			@PathVariable("linkId") String linkId, @Valid @RequestBody Link link) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> linkList = landingPage.getYoutubeLinkList();
			if (linkList != null) {
				for (int i = 0; i < linkList.size(); i++) {
					if (linkList.get(i).getId().equals(linkId)) {
						linkList.set(i, link);
						break;
					}
				}
				landingPage.setYoutubeLinkList(linkList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-youtube-link/{linkId}")
	public ResponseEntity<LandingPage> deleteYoutubeLink(@PathVariable("id") String id,
			@PathVariable("linkId") String linkId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<Link> faqList = landingPage.getYoutubeLinkList();
			if (faqList != null) {
				for (int i = 0; i < faqList.size(); i++) {
					if (faqList.get(i).getId().equals(linkId)) {
						faqList.remove(i);
						break;
					}
				}
				landingPage.setYoutubeLinkList(faqList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-category")
	public ResponseEntity<LandingPage> saveCategory(@PathVariable("id") String id, @Valid @RequestBody CandidateCategory category) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> categoryList = new ArrayList<CandidateCategory>();
			if (landingPage.getCategoryList() == null) {
				categoryList.add(category);
			} else {
				categoryList.addAll(landingPage.getCategoryList());
				categoryList.add(category);
			}
			landingPage.setCategoryList(categoryList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-category/{cateId}")
	public ResponseEntity<CandidateCategory> getcategory(@PathVariable("id") String id, @PathVariable("cateId") String cateId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> categoryList = landingPage.getCategoryList();
			if (categoryList != null) {
				for (int i = 0; i < categoryList.size(); i++) {
					if (categoryList.get(i).getId().equals(cateId)) {
						return ResponseEntity.ok(categoryList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-categroy/{cateId}")
	public ResponseEntity<LandingPage> updateCategory(@PathVariable("id") String id, @PathVariable("cateId") String cateId,
			@Valid @RequestBody CandidateCategory category) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> categoryList = landingPage.getCategoryList();
			if (categoryList != null) {
				for (int i = 0; i < categoryList.size(); i++) {
					if (categoryList.get(i).getId().equals(cateId)) {
						categoryList.set(i, category);
						break;
					}
				}
				landingPage.setCategoryList(categoryList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-category/{cateId}")
	public ResponseEntity<LandingPage> deleteCategory(@PathVariable("id") String id,
			@PathVariable("cateId") String cateId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> categoryList = landingPage.getCategoryList();
			if (categoryList != null) {
				for (int i = 0; i < categoryList.size(); i++) {
					if (categoryList.get(i).getId().equals(cateId)) {
						categoryList.remove(i);
						break;
					}
				}
				landingPage.setCategoryList(categoryList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-department")
	public ResponseEntity<LandingPage> saveDepartment(@PathVariable("id") String id, @Valid @RequestBody CandidateCategory department) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> departmentList = new ArrayList<CandidateCategory>();
			if (landingPage.getDepartmentList() == null) {
				departmentList.add(department);
			} else {
				departmentList.addAll(landingPage.getDepartmentList());
				departmentList.add(department);
			}
			landingPage.setDepartmentList(departmentList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-department/{deptId}")
	public ResponseEntity<CandidateCategory> getdepartment(@PathVariable("id") String id, @PathVariable("deptId") String deptId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> departmentList = landingPage.getDepartmentList();
			if (departmentList != null) {
				for (int i = 0; i < departmentList.size(); i++) {
					if (departmentList.get(i).getId().equals(deptId)) {
						return ResponseEntity.ok(departmentList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-department/{deptId}")
	public ResponseEntity<LandingPage> updateDepartment(@PathVariable("id") String id, @PathVariable("deptId") String deptId,
			@Valid @RequestBody CandidateCategory department) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> departmentList = landingPage.getDepartmentList();
			if (departmentList != null) {
				for (int i = 0; i < departmentList.size(); i++) {
					if (departmentList.get(i).getId().equals(deptId)) {
						departmentList.set(i, department);
						break;
					}
				}
				landingPage.setDepartmentList(departmentList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-department/{deptId}")
	public ResponseEntity<LandingPage> deleteDepartment(@PathVariable("id") String id,
			@PathVariable("deptId") String deptId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> departmentList = landingPage.getDepartmentList();
			if (departmentList != null) {
				for (int i = 0; i < departmentList.size(); i++) {
					if (departmentList.get(i).getId().equals(deptId)) {
						departmentList.remove(i);
						break;
					}
				}
				landingPage.setDepartmentList(departmentList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-practicetag")
	public ResponseEntity<LandingPage> savePracticetag(@PathVariable("id") String id, @Valid @RequestBody CandidateCategory practicetag) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> practicetagList = new ArrayList<CandidateCategory>();
			if (landingPage.getPracticetagList() == null) {
				practicetagList.add(practicetag);
			} else {
				practicetagList.addAll(landingPage.getPracticetagList());
				practicetagList.add(practicetag);
			}
			landingPage.setPracticetagList(practicetagList);
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-practicetag/{practId}")
	public ResponseEntity<CandidateCategory> getpracticetag(@PathVariable("id") String id, @PathVariable("practId") String practId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> practicetagList = landingPage.getPracticetagList();
			if (practicetagList != null) {
				for (int i = 0; i < practicetagList.size(); i++) {
					if (practicetagList.get(i).getId().equals(practId)) {
						return ResponseEntity.ok(practicetagList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-practicetag/{practId}")
	public ResponseEntity<LandingPage> updatePracticetag(@PathVariable("id") String id, @PathVariable("practId") String practId,
			@Valid @RequestBody CandidateCategory practicetag) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> practicetagList = landingPage.getPracticetagList();
			if (practicetagList != null) {
				for (int i = 0; i < practicetagList.size(); i++) {
					if (practicetagList.get(i).getId().equals(practId)) {
						practicetagList.set(i, practicetag);
						break;
					}
				}
				landingPage.setPracticetagList(practicetagList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-practicetag/{practId}")
	public ResponseEntity<LandingPage> deletePracticetag(@PathVariable("id") String id,
			@PathVariable("practId") String practId) {
		if (landingPageRepository.existsById(id)) {
			LandingPage landingPage = landingPageRepository.findById(id).get();
			List<CandidateCategory> practicetagList = landingPage.getPracticetagList();
			if (practicetagList != null) {
				for (int i = 0; i < practicetagList.size(); i++) {
					if (practicetagList.get(i).getId().equals(practId)) {
						practicetagList.remove(i);
						break;
					}
				}
				landingPage.setPracticetagList(practicetagList);
			}
			return ResponseEntity.ok(landingPageRepository.save(landingPage));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
