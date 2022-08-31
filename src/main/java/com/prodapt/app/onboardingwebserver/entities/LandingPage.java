package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-landing-page")
public class LandingPage implements Serializable {

	private static final long serialVersionUID = 8411557145711164568L;

	@Id
	private String id;

	@NotNull
	@NotBlank
	@Indexed
	private String pageName;

	private List<ContactSpoc> hrbpSpocList;
	private List<ContactSpoc> proboardingAdvisorList;
	private List<ContactSpoc> buddyList;
	private List<CandidateCategory> categoryList;
	private List<CandidateCategory> departmentList;
	private List<CandidateCategory> practicetagList;
	private List<FAQ> faqList;
	private List<HomeDoc> homeDocList;
	private List<Link> youtubeLinkList;
	private List<Link> linkList;

	private long created;
	private long modified;

	@Indexed
	private String modifiedBy;

	public LandingPage() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}

	public LandingPage(@NotNull @NotBlank String pageName, List<ContactSpoc> hrbpSpocList,
			List<ContactSpoc> proboardingAdvisorList,List<CandidateCategory> categoryList,
			List<CandidateCategory> departmentList, List<CandidateCategory> practicetagList, List<FAQ> faqList,
			List<Link> youtubeLinkList, List<Link> linkList, String modifiedBy,List<ContactSpoc> buddyList) {
		super();
		this.pageName = pageName;
		this.hrbpSpocList = hrbpSpocList;
		this.proboardingAdvisorList = proboardingAdvisorList;
		this.buddyList = buddyList;
		this.categoryList = categoryList;
		this.departmentList = departmentList;
		this.practicetagList = practicetagList;
		this.faqList = faqList;
		this.youtubeLinkList = youtubeLinkList;
		this.linkList = linkList;
		this.modifiedBy = modifiedBy;
	}

	public List<HomeDoc> getHomeDocList() {
		return homeDocList;
	}

	public void setHomeDocList(List<HomeDoc> homeDocList) {
		this.homeDocList = homeDocList;
	}

	public List<ContactSpoc> getProboardingAdvisorList() {
		return proboardingAdvisorList;
	}

	public void setProboardingAdvisorList(List<ContactSpoc> proboardingAdvisorList) {
		this.proboardingAdvisorList = proboardingAdvisorList;
	}

	public List<Link> getYoutubeLinkList() {
		return youtubeLinkList;
	}

	public void setYoutubeLinkList(List<Link> youtubeLinkList) {
		this.youtubeLinkList = youtubeLinkList;
	}

	public String getId() {
		return id;
	}

	public String getPageName() {
		return pageName;
	}

	public List<ContactSpoc> getHrbpSpocList() {
		return hrbpSpocList;
	}

	public List<FAQ> getFaqList() {
		return faqList;
	}

	public List<Link> getLinkList() {
		return linkList;
	}

	public long getCreated() {
		return created;
	}

	public long getModified() {
		return modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public void setHrbpSpocList(List<ContactSpoc> hrbpSpocList) {
		this.hrbpSpocList = hrbpSpocList;
	}

	public void setFaqList(List<FAQ> faqList) {
		this.faqList = faqList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}
	
	public List<CandidateCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CandidateCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<CandidateCategory> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<CandidateCategory> departmentList) {
		this.departmentList = departmentList;
	}

	public List<CandidateCategory> getPracticetagList() {
		return practicetagList;
	}

	public void setPracticetagList(List<CandidateCategory> practicetagList) {
		this.practicetagList = practicetagList;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<ContactSpoc> getBuddyList() {
		return buddyList;
	}

	public void setBuddyList(List<ContactSpoc> buddyList) {
		this.buddyList = buddyList;
	}

}