package com.feerlaroc.zohos.schema.pojo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.feerlaroc.core.entity.EntityInterface;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contact_id",
        "contact_name",
        "company_name",
        "contact_type",
        "status",
        "source",
        "is_linked_with_zohocrm",
        "payment_terms",
        "payment_terms_label",
        "currency_id",
        "currency_code",
        "outstanding_receivable_amount",
        "unused_credits_receivable_amount",
        "first_name",
        "last_name",
        "email",
        "phone",
        "mobile",
        "created_time",
        "last_modified_time"
})
public class Contact implements EntityInterface{

    @JsonProperty("contact_id")
    private String contactId;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("contact_type")
    private String contactType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("source")
    private String source;
    @JsonProperty("is_linked_with_zohocrm")
    private Boolean isLinkedWithZohocrm;
    @JsonProperty("payment_terms")
    private Float paymentTerms;
    @JsonProperty("payment_terms_label")
    private String paymentTermsLabel;
    @JsonProperty("currency_id")
    private String currencyId;
    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("outstanding_receivable_amount")
    private Float outstandingReceivableAmount;
    @JsonProperty("unused_credits_receivable_amount")
    private Float unusedCreditsReceivableAmount;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("last_modified_time")
    private String lastModifiedTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Contact(){}

    @Override
    public String id() {
        return contactId;
    }

    @Override
    public String DBKey() {
        return "contacts";
    }

    @Override
    public <T> T getValue(Class<T> clazz) {

        Contact c = this;

        return (T) c;

    }

    /**
     *
     * @return
     * The contactId
     */
    @JsonProperty("contact_id")
    public String getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId
     * The contact_id
     */
    @JsonProperty("contact_id")
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    /**
     *
     * @return
     * The contactName
     */
    @JsonProperty("contact_name")
    public String getContactName() {
        return contactName;
    }

    /**
     *
     * @param contactName
     * The contact_name
     */
    @JsonProperty("contact_name")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *
     * @return
     * The companyName
     */
    @JsonProperty("company_name")
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName
     * The company_name
     */
    @JsonProperty("company_name")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @return
     * The contactType
     */
    @JsonProperty("contact_type")
    public String getContactType() {
        return contactType;
    }

    /**
     *
     * @param contactType
     * The contact_type
     */
    @JsonProperty("contact_type")
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     * The isLinkedWithZohocrm
     */
    @JsonProperty("is_linked_with_zohocrm")
    public Boolean getIsLinkedWithZohocrm() {
        return isLinkedWithZohocrm;
    }

    /**
     *
     * @param isLinkedWithZohocrm
     * The is_linked_with_zohocrm
     */
    @JsonProperty("is_linked_with_zohocrm")
    public void setIsLinkedWithZohocrm(Boolean isLinkedWithZohocrm) {
        this.isLinkedWithZohocrm = isLinkedWithZohocrm;
    }

    /**
     *
     * @return
     * The paymentTerms
     */
    @JsonProperty("payment_terms")
    public Float getPaymentTerms() {
        return paymentTerms;
    }

    /**
     *
     * @param paymentTerms
     * The payment_terms
     */
    @JsonProperty("payment_terms")
    public void setPaymentTerms(Float paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    /**
     *
     * @return
     * The paymentTermsLabel
     */
    @JsonProperty("payment_terms_label")
    public String getPaymentTermsLabel() {
        return paymentTermsLabel;
    }

    /**
     *
     * @param paymentTermsLabel
     * The payment_terms_label
     */
    @JsonProperty("payment_terms_label")
    public void setPaymentTermsLabel(String paymentTermsLabel) {
        this.paymentTermsLabel = paymentTermsLabel;
    }

    /**
     *
     * @return
     * The currencyId
     */
    @JsonProperty("currency_id")
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     *
     * @param currencyId
     * The currency_id
     */
    @JsonProperty("currency_id")
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    /**
     *
     * @return
     * The currencyCode
     */
    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     *
     * @param currencyCode
     * The currency_code
     */
    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     *
     * @return
     * The outstandingReceivableAmount
     */
    @JsonProperty("outstanding_receivable_amount")
    public Float getOutstandingReceivableAmount() {
        return outstandingReceivableAmount;
    }

    /**
     *
     * @param outstandingReceivableAmount
     * The outstanding_receivable_amount
     */
    @JsonProperty("outstanding_receivable_amount")
    public void setOutstandingReceivableAmount(Float outstandingReceivableAmount) {
        this.outstandingReceivableAmount = outstandingReceivableAmount;
    }

    /**
     *
     * @return
     * The unusedCreditsReceivableAmount
     */
    @JsonProperty("unused_credits_receivable_amount")
    public Float getUnusedCreditsReceivableAmount() {
        return unusedCreditsReceivableAmount;
    }

    /**
     *
     * @param unusedCreditsReceivableAmount
     * The unused_credits_receivable_amount
     */
    @JsonProperty("unused_credits_receivable_amount")
    public void setUnusedCreditsReceivableAmount(Float unusedCreditsReceivableAmount) {
        this.unusedCreditsReceivableAmount = unusedCreditsReceivableAmount;
    }

    /**
     *
     * @return
     * The firstName
     */
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The lastName
     */
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The phone
     */
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The mobile
     */
    @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    @JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The createdTime
     */
    @JsonProperty("created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     *
     * @param createdTime
     * The created_time
     */
    @JsonProperty("created_time")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *
     * @return
     * The lastModifiedTime
     */
    @JsonProperty("last_modified_time")
    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     *
     * @param lastModifiedTime
     * The last_modified_time
     */
    @JsonProperty("last_modified_time")
    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
