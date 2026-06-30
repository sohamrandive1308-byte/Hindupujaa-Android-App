package com.example.hindupujaa.core.data.util

import com.example.hindupujaa.core.domain.model.Category
import com.example.hindupujaa.core.domain.model.KitItem
import com.example.hindupujaa.core.domain.model.Puja
import com.example.hindupujaa.core.domain.model.StoreProduct
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSeeder @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun seedDatabase() {
        seedCategories()
        seedPujas()
        seedAppConfig()
        seedStoreProducts()
    }

    private fun seedCategories() {
        val categories = listOf(
            Category("1", "Home", "", "Pujas for home and vastu"),
            Category("2", "Prosperity", "", "Pujas for wealth and success"),
            Category("3", "Life Events", "", "Pujas for special life milestones"),
            Category("4", "Business", "", "Pujas for office and business growth"),
            Category("5", "Healing", "", "Pujas for health and wellness"),
            Category("6", "Festival", "", "Special festival pujas"),
            Category("7", "Protection", "", "Pujas for divine protection"),
            Category("8", "Construction", "", "Pujas for building and land")
        )
        categories.forEach { firestore.collection("categories").document(it.id).set(it) }
    }

    private fun seedPujas() {
        firestore.collection("pujas").get().addOnSuccessListener { snapshot ->
            if (snapshot.isEmpty) {
                val pujas = listOf(
                    Puja(
                        id = "satyanarayan-puja",
                        nameEn = "Satyanarayan Puja Kit",
                        nameHi = "सत्यनारायण पूजा",
                        slug = "satyanarayan-puja",
                        category = "Prosperity",
                        durationLabel = "3-4 hrs",
                        isTrending = true,
                        heroImagePath = "pujas/satyanarayanpuja.png",
                        backstory = "The Satyanarayan Puja is a ritual performed by Hindus before or after the fulfillment of their desires.",
                        benefits = listOf("Family peace", "Prosperity", "Fulfillment of desires"),
                        basePrice = 2500.0,
                        sortOrder = 1
                    ),
                    Puja(
                        id = "vastu-shanti-puja",
                        nameEn = "Vastu Shanti Puja Kit",
                        nameHi = "वास्तु शांति पूजा",
                        slug = "vastu-shanti-puja",
                        category = "Home",
                        durationLabel = "4-5 hrs",
                        heroImagePath = "pujas/vastushantiipuja.png",
                        basePrice = 3500.0,
                        sortOrder = 2
                    ),
                    Puja(
                        id = "griha-pravesh-puja",
                        nameEn = "Griha Pravesh Puja Kit",
                        nameHi = "गृह प्रवेश पूजा",
                        slug = "griha-pravesh-puja",
                        category = "Home",
                        durationLabel = "3-4 hrs",
                        heroImagePath = "pujas/grihapraveshpuja.png",
                        basePrice = 5000.0,
                        sortOrder = 3
                    ),
                    Puja(
                        id = "bhumi-puja",
                        nameEn = "Bhumi Puja Kit",
                        nameHi = "भूमि पूजा",
                        slug = "bhumi-puja",
                        category = "Construction",
                        durationLabel = "2-3 hrs",
                        heroImagePath = "pujas/bhumipuja.png",
                        basePrice = 3000.0,
                        sortOrder = 4
                    ),
                    Puja(
                        id = "durga-puja",
                        nameEn = "Durga Puja Kit",
                        nameHi = "दुर्गा पूजा",
                        slug = "durga-puja",
                        category = "Protection",
                        durationLabel = "4-5 hrs",
                        heroImagePath = "pujas/durgapuja.png",
                        basePrice = 4500.0,
                        sortOrder = 5
                    ),
                    Puja(
                        id = "engagement-puja",
                        nameEn = "Engagement Puja Kit (Sakharpuda)",
                        nameHi = "साखरपुडा पूजा",
                        slug = "engagement-puja",
                        category = "Life Events",
                        durationLabel = "2 hrs",
                        heroImagePath = "pujas/engagementpuja.png",
                        basePrice = 2000.0,
                        sortOrder = 6
                    ),
                    Puja(
                        id = "mahalakshmi-puja",
                        nameEn = "Mahalakshmi Puja Kit",
                        nameHi = "महालक्ष्मी पूजा",
                        slug = "mahalakshmi-puja",
                        category = "Prosperity",
                        durationLabel = "2-3 hrs",
                        heroImagePath = "pujas/mahalakshmipuja.png",
                        basePrice = 3000.0,
                        sortOrder = 7
                    ),
                    Puja(
                        id = "ganesh-puja",
                        nameEn = "Ganesh Puja Kit",
                        nameHi = "गणेश पूजा",
                        slug = "ganesh-puja",
                        category = "Prosperity",
                        durationLabel = "1.5 hrs",
                        heroImagePath = "pujas/ganeshpuja.png",
                        basePrice = 1500.0,
                        sortOrder = 8
                    ),
                    Puja(
                        id = "laghu-rudrabhishek",
                        nameEn = "Laghu Rudrabhishek Kit",
                        nameHi = "लघु रुद्राभिषेक",
                        slug = "laghu-rudrabhishek",
                        category = "Healing",
                        durationLabel = "3 hrs",
                        heroImagePath = "pujas/laghururdrabhishekpuja.png",
                        basePrice = 5500.0,
                        sortOrder = 9
                    ),
                    Puja(
                        id = "office-opening-puja",
                        nameEn = "Office Opening Puja Kit",
                        nameHi = "ऑफिस ओपनिंग पूजा",
                        slug = "office-opening-puja",
                        category = "Business",
                        durationLabel = "2-3 hrs",
                        heroImagePath = "pujas/officeopeningpuja.png",
                        basePrice = 4000.0,
                        sortOrder = 10
                    ),
                    Puja(
                        id = "rudrabhishek-puja",
                        nameEn = "Rudrabhishek Puja Kit",
                        nameHi = "रुद्राभिषेक पूजा",
                        slug = "rudrabhishek-puja",
                        category = "Healing",
                        durationLabel = "2 hrs",
                        heroImagePath = "pujas/rudrabhishekpuja.png",
                        basePrice = 3500.0,
                        sortOrder = 11
                    ),
                    Puja(
                        id = "mangalagaur-puja",
                        nameEn = "Mangalagaur Puja Kit",
                        nameHi = "मंगळागौर पूजा",
                        slug = "mangalagaur-puja",
                        category = "Festival",
                        durationLabel = "4 hrs",
                        heroImagePath = "pujas/mangalagaurpuja.png",
                        basePrice = 2500.0,
                        sortOrder = 12
                    ),
                    Puja(
                        id = "navchandi-puja",
                        nameEn = "Navchandi Puja Kit",
                        nameHi = "नवचंडी पूजा",
                        slug = "navchandi-puja",
                        category = "Protection",
                        durationLabel = "5-6 hrs",
                        heroImagePath = "pujas/navchandipuja.png",
                        basePrice = 8000.0,
                        sortOrder = 13
                    )
                )

                pujas.forEach { puja ->
                    firestore.collection("pujas").document(puja.id).set(puja)
                    seedKitItems(puja.id)
                }
            }
        }
    }

    private fun seedKitItems(pujaId: String) {
        val items = when (pujaId) {
            "satyanarayan-puja" -> listOf(
                KitItem("s1", "Satyanarayan Photo", "सत्यनारायण फोटो", "Standard Size", "included", 50.0),
                KitItem("s2", "Mango Leaves", "आंब्याची पाने", "25 fresh leaves", "included", 40.0, isPerishable = true),
                KitItem("s3", "Banana Stems/Trunks (4+2)", "केळीची खुंट", "Fresh stems", "included", 40.0, isPerishable = true),
                KitItem("s4", "Haldi, Kumkum, Gulal, Bukka Set", "हळद-कुंकू-गुलाल-बुक्का", "Set", "included", 60.0),
                KitItem("s5", "Puja Rice 10g", "तांदूळ", "10g", "included", 10.0),
                KitItem("s6", "Rangoli Pack", "रांगोळी", "Standard Pack", "included", 30.0),
                KitItem("s7", "Incense Box", "अगरबत्ती", "1 Box", "included", 50.0),
                KitItem("s8", "Large Camphor 25pcs", "कापूर मोठे", "25 pieces", "included", 100.0),
                KitItem("s9", "Janve Jodi (1 Pair)", "जानवे जोडी", "1 Pair", "included", 30.0),
                KitItem("s10", "Supari 25pcs", "सुपारी", "25 pieces", "included", 150.0),
                KitItem("s11", "Kharik, Badam, Haldund Set", "खारीक-बदाम-हळकुंड", "Set", "included", 120.0),
                KitItem("s12", "Coconut 2pcs", "नारळ", "2 pieces", "included", 80.0),
                KitItem("s13", "Dry Coconut Pieces & Vati", "खोबरे तुकडे व वाटी", "Set", "included", 90.0),
                KitItem("s14", "Red Cloth 1 Meter", "लाल वस्त्र", "1 Meter", "included", 120.0),
                KitItem("s15", "Honey 50g", "मध", "50g", "included", 60.0),
                KitItem("s16", "Small Attar Bottle", "अत्तर छोटी बाटली", "1 Unit", "included", 40.0),
                KitItem("s17", "Garland Set", "हार", "Fresh Flower Set", "included", 350.0, isPerishable = true),
                KitItem("s18", "Flowers, Durva, Tulsi, Bel", "फुलं, दुर्वा, तुळस, बेल", "Fresh Set", "included", 200.0, isPerishable = true),
                KitItem("s19", "5 Types of Fruits", "फळं", "Fresh Fruits", "included", 300.0, isPerishable = true),
                KitItem("s20", "Banana 1 Dozen", "केळी", "1 Dozen", "included", 80.0, isPerishable = true),
                KitItem("s21", "Vidhyachi Pane 25pcs", "विड्याची पाने", "25 pieces", "included", 100.0, isPerishable = true),
                KitItem("r1", "Wooden Chaurang Rental", "चौरंग (Rental)", "Wooden Altar", "rental", 200.0, isDefaultSelected = false),
                KitItem("r2", "Wooden Paat Rental 3pcs", "बसायचे पाट (Rental)", "3 Pieces", "rental", 150.0, isDefaultSelected = false),
                KitItem("r3", "Standing Lamps Rental 2 Units", "समई (Rental)", "Brass Lamps", "rental", 250.0, isDefaultSelected = false),
                KitItem("r4", "Small Oil Lamp Rental", "निरंजन (Rental)", "1 Unit", "rental", 250.0, isDefaultSelected = false),
                KitItem("r5", "Incense Stick Stand Rental", "अगरबत्ती स्टैंड (Rental)", "1 Unit", "rental", 250.0, isDefaultSelected = false),
                KitItem("r6", "Puja Bell Rental", "घंटी (Rental)", "1 Unit", "rental", 250.0, isDefaultSelected = false),
                KitItem("r7", "Copper Ritual Set Rental", "ताम्हण, तांबे, पळी (Rental)", "Full Set", "rental", 250.0, isDefaultSelected = false)
            )
            "vastu-shanti-puja" -> listOf(
                KitItem("v1", "Vastu Purusha Brass Idol", "वास्तु प्रतिमा", "Brass", "included", 150.0),
                KitItem("v2", "Betel Leaves with Stems 25pcs", "देठासह विड्याची पाने", "25 fresh leaves", "included", 150.0, isPerishable = true),
                KitItem("v3", "Betel Nuts 151pcs", "सुपार्‍या", "151 pieces", "included", 150.0),
                KitItem("v4", "Coconuts 11pcs", "नारळ", "11 pieces", "included", 150.0),
                KitItem("v5", "Mango Twigs 5pcs", "आंब्याचे डहाळे", "5 fresh twigs", "included", 150.0, isPerishable = true),
                KitItem("v6", "Homa Samagri Pack", "होम सामुग्री", "Pack", "included", 150.0),
                KitItem("v7", "Samidha Bundles 10pcs", "समिधा जुडी", "10 bundles", "included", 180.0),
                KitItem("v8", "Mango Wood 0.5kg", "आंब्याची लाकडे", "0.5kg", "included", 180.0),
                KitItem("v9", "Pure Ghee 500g", "शुद्ध तूप", "500g", "included", 180.0),
                KitItem("v10", "Sacred Soil & Gaumutra", "हत्तीची माती व गौमूत्र", "Set", "included", 180.0),
                KitItem("v11", "Cloth Set", "वस्त्र संच", "Red, Yellow, White", "included", 180.0),
                KitItem("v12", "Apparel for Gifting", "ब्लाउज पीस, खण", "Set", "included", 250.0),
                KitItem("v13", "New Winnow/Sup + New Broom", "नवीन सूप व झाडू", "Set", "included", 180.0),
                KitItem("v14", "Puja Essentials", "पूजा साहित्य", "Set", "included", 180.0),
                KitItem("v15", "Aromatic Set", "सुगंधी संच", "Set", "included", 180.0),
                KitItem("v16", "Holy Thread (Janve) 2pcs", "जानवी", "2 pieces", "included", 180.0),
                KitItem("v17", "Sandalwood Paste/Powder", "चंदन", "Set", "included", 180.0),
                KitItem("v18", "Grains Set", "धान्य संच", "Set", "included", 180.0),
                KitItem("v19", "Fresh Flora", "फुलं व पाने", "Fresh Set", "included", 180.0, isPerishable = true),
                KitItem("v20", "Fruits 10pcs", "फळं", "10 pieces", "included", 180.0, isPerishable = true),
                KitItem("v21", "Panchamrit", "पंचामृत", "Set", "included", 180.0, isPerishable = true),
                KitItem("v22", "Aarti Thali", "आरती थाळी", "Set", "included", 180.0),
                KitItem("v23", "Prasad & Sweets", "प्रसाद", "Set", "included", 180.0, isPerishable = true),
                KitItem("v24", "Loose Cash ₹21", "दक्षिणा", "₹21", "included", 21.0),
                KitItem("v25", "Vastu Dosha Nivaran Yantra", "वास्तु यंत्र", "1 Unit", "included", 180.0),
                KitItem("vr1", "Havan Kund Rental", "होम कुंड (Rental)", "Rental", "rental", 300.0, isDefaultSelected = false),
                KitItem("vr2", "Wooden Altar (Chaurang) Rental", "चौरंग (Rental)", "Rental", "rental", 200.0, isDefaultSelected = false),
                KitItem("vr3", "Wooden Seating Planks Rental", "बसायचे पाट (Rental)", "Rental", "rental", 150.0, isDefaultSelected = false),
                KitItem("vr4", "Brass Standing Lamps Rental", "समई (Rental)", "Rental", "rental", 250.0, isDefaultSelected = false),
                KitItem("vr5", "Puja Bell Rental", "घंटा (Rental)", "Rental", "rental", 250.0, isDefaultSelected = false),
                KitItem("vr6", "Copper Ritual Set Rental", "ताम्हण, तांबे, पळी (Rental)", "Rental", "rental", 250.0, isDefaultSelected = false)
            )
            else -> listOf(
                KitItem("p1", "Basic Samagri", "पूजा साहित्य", "Standard Kit", "included", 500.0)
            )
        }

        items.forEach { item ->
            firestore.collection("pujas").document(pujaId).collection("kit_items").document(item.id).set(item)
        }
    }

    private fun seedAppConfig() {
        firestore.collection("app_config").document("global").get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {
                val config = mapOf(
                    "delivery_cities" to listOf("Pune", "Nagpur"),
                    "whatsapp_number" to "919175799251",
                    "maintenance_mode" to false,
                    "banner_title" to "Shubh Puja - Welcome to HinduPujaa!",
                    "terms_and_conditions" to "By ordering, you agree to our freshness guarantee and rental item terms."
                )
                firestore.collection("app_config").document("global").set(config)
            }
        }
    }

    private fun seedStoreProducts() {
        firestore.collection("store_products").get().addOnSuccessListener { snapshot ->
            if (snapshot.isEmpty) {
                val products = listOf(
                    StoreProduct("p1", "Pure Cow Ghee 500g", "Consumables", "500g", 600.0, 550.0, 8, "PREMIUM", "products/ghee.png", 100, true, 1),
                    StoreProduct("p2", "Sandalwood Incense Sticks", "Puja Items", "Pack of 50", 150.0, 120.0, 20, "BESTSELLER", "products/incense.png", 200, true, 2),
                    StoreProduct("p3", "Brass Diya Large", "Puja Items", "1 Unit", 800.0, 699.0, 12, "POPULAR", "products/diya.png", 50, true, 3),
                    StoreProduct("p4", "Rudraksha Mala (108 Beads)", "Rudraksha", "1 Unit", 1200.0, 999.0, 16, "RARE", "products/rudraksha.png", 30, true, 4),
                    StoreProduct("p5", "Gangajal 1L", "Consumables", "1 Litre", 200.0, 180.0, 10, "VALUE", "products/gangajal.png", 150, true, 5),
                    StoreProduct("p6", "Panchadhatu Ganesh Idol", "Puja Items", "1 Unit", 2500.0, 2100.0, 16, "PREMIUM", "products/ganesh.png", 20, true, 6)
                )
                products.forEach { firestore.collection("store_products").document(it.id).set(it) }
            }
        }
    }
}
