package com.acko.userprofile.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@Document(collection = "user_profiles")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "user_profiles")
public class UserData {
    @Id
    private String id;

    @Field(type = FieldType.Object)
    private UserProfile userProfile;

    @Field(type = FieldType.Object)
    private UserAssets userAssets;

    @Data
    public static class UserProfile {
        private PersonalProfile personalProfile;
        private CreditProfile creditProfile;
        private HealthProfile healthProfile;
        private AckoProfile ackoProfile;
        private List<FamilyMember> familyProfile;
        private DeviceProfile deviceProfile;
        private ScoreProfile scoreProfile;
    }

    @Data
    public static class PersonalProfile {
        private int userId;
        private String phoneNumber;
        private List<Email> emails;
        private List<Name> names;
        private List<Dob> dobs;
        private List<Address> addresses;
        private List<Pincode> pincodes;
        private List<City> cities;
        private List<State> states;
        private List<Gender> genders;
        private String profession;
        private String maritalStatus;
        private List<BankAccountDetails> bankAccountDetails;
        private Boolean isBlocklisted;
        private Boolean isOlaCustomer;
        private Boolean isAutoUser;
        private String trackerId;
    }

    @Data
    public static class Email {
        private String email;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class Name {
        private String name;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class Dob {
        private String dob;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class Address {
        private String address;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class Pincode {
        private String pincode;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class City {
        private String city;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class State {
        private String state;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class Gender {
        private String gender;
        private List<String> sources;
        private String recency;
        private int repeatation;
        private double confidenceScore;
    }

    @Data
    public static class BankAccountDetails {
        private String bankName;
        private String accountHolder;
        private String upi;
        private String accountNumber;
    }

    @Data
    public static class CreditProfile {
        private int cibilScore;
        private int experianRank;
    }

    @Data
    public static class HealthProfile {
        private String smokeTobacco;
        private String alcohol;
        private String wasHospitalised;
        private String outcomeLabTest;
    }

    @Data
    public static class AckoProfile {
        private List<String> ivrPreferredLanguage;
        private Boolean whatsappNumberOptin;
        private String firstPolicyBoughtOn;
        private Boolean whatsappConsent;
    }

    @Data
    public static class FamilyMember {
        private String name;
        private int age;
        private String dob;
        private String relation;
    }

    @Data
    public static class DeviceProfile {
        private String appData;
        private int numberOfDevice;
    }

    @Data
    public static class ScoreProfile {
        private CustomerLoyalityScore customerLoyalityScore;
        private AutoDecileScore autoDecileScore;
    }

    @Data
    public static class CustomerLoyalityScore {
        private String scoreValue;
        private ScoreParameters scoreParameters;
    }

    @Data
    public static class ScoreParameters {
        private String activeTenure;
        private String d2cActivePolicies;
        private String nonD2cActivePolicies;
        private String lobCount;
        private String experianRank;
        private String partnershipTotalPolicy;
    }

    @Data
    public static class AutoDecileScore {
        private int scoreValue;
        private AutoRiskScore scoreParameters;
    }

    @Data
    public static class AutoRiskScore {
        private double autoRiskScore;
    }

    @Data
    public static class UserAssets {
        private List<Vehicle> car;
        private List<Vehicle> bike;
        private Health health;
        private String travel;
        private List<Appliance> appliances;
        private List<Other> others;
    }

    @Data
    public static class Vehicle {
        private AssetDetails assetDetails;
        private Boolean coveredByAcko;
        private List<PolicyDetails> policyDetails;
    }

    @Data
    public static class AssetDetails {
        private String make;
        private String model;
        private String variant;
        private String exShowroom;
        private String cc;
        private String fuelType;
        private String registrationNumber;
        private String paymentMethod;
        private int noOfOwner;
        private Boolean isCommercial;
        private String vahanApiPulledAt;
        private int registrationYear;
    }

    @Data
    public static class PolicyDetails {
        private List<Object> claimDetails;
        private String policyType;
        private String policyStatus;
        private String policyBoughtOn;
        private String activeTenure;
        private String nextRenewalDate;
        private String policyStartDate;
        private String policyEndDate;
        private int idv;
        private String premium;
        private String policyNumber;
        private String policyId;
        private String policyUHID;
        private String renewalFrequency;
        private Boolean isAssisted;
        private Boolean isPIDone;
    }

    @Data
    public static class Health {
        private List<FamilyMember> familyMemberDetails;
        private Boolean coveredByAcko;
        private List<PolicyDetails> policyDetails;
    }

    @Data
    public static class Appliance {
        private List<ApplianceDetails> applianceDetails;
        private Boolean coveredByAcko;
        private List<PolicyDetails> policyDetails;
    }

    @Data
    public static class ApplianceDetails {
        private String name;
        private String make;
        private String model;
        private double price;
        private String paymentMethod;
    }

    @Data
    public static class Other {
        private String category;
        private OtherPolicyDetails policyDetails;
        private OtherClaimDetails claimDetails;
    }

    @Data
    public static class OtherPolicyDetails {
        private String firstPolicyBoughtOn;
        private double totalPremiumPaid;
        private int totalPolicies;
    }

    @Data
    public static class OtherClaimDetails {
        private String firstClaimRaisedOn;
        private double totalAmountSettled;
        private int totalClaimsRaised;
    }
}