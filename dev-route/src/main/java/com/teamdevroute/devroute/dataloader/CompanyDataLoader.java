package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class CompanyDataLoader {
    @Autowired
    private CompanyRepository companyRepository;

    private final List<Company> companyList = new ArrayList<>();

    @Transactional
    public void loadCompanyData() {
        companyList.add(
            createCompany(
                "(주)티몬", "4,774", "티몬(TMON)은 2010년 창립을 시작으로 이커머스 시장을 선도하는 기업입니다.\n" +
                            "약 1000명의 사원이 근무 중이며,\n" +
                            "기업 가치 1조를 돌파하여 유니콘기업으로 선정 되는 등, 최근 가파른 성장세를 보이고 있습니다.\n" +
                            "또한 2020년 3월 월간 흑자를 달성하여 이커머스 업계 최초로 흑자 전환에 성공하였습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/50695/thumb_123123.jpg",
                2.8
            )
        );
        companyList.add(
            createCompany(
                "(주)위메프", "4,581", "반값습니다. 놀라운 가격과 트렌드를 발견하는 즐거움이 있는 위메프입니다.\n" +
                            "위메프는 2010년 “우리가 가격을 만든다”라는 생각으로 ‘위메이크프라이스(WE MAKE PRICE)’서비스를 시작했습니다. 온라인 쇼핑 없는 일상을 생각할 수 없는 지금, 위메프는 사용자를 위한 더 나은 쇼핑 경험, 더 나은 플랫폼에 대해 고심하고 있습니다.\n" +
                            "\n" +
                            "“더 좋은 가격을 쉽게 발견할 수 있는 방법은 무엇일까?”\n" +
                            "“어떻게 하면 위메프에서 보내는 시간이 더 즐거울 수 있을까?”\n" +
                            "\n" +
                            "위메프는 사용자분들께 주변에 추천하고 싶을 정도의 즐거움을 드리기 위해 큐레이션과 플랫폼에 집중하고 있으며 새로운 도전을 통해 성장하고, 위메프의 내일을 같이 만들어갈 동료를 기다리고 있습니다. 서로에게 건강한 자극제가 되어 성장의 기쁨을 느끼고 싶다면 위메프의 동료가 되어주세요!",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/60632/thumb_%ED%94%84%EB%A1%9C%ED%95%84_2022.jpg",
                2.6
            )
        );
        companyList.add(
            createCompany(
                "(주)엘지유플러스", "5,595", "(주)엘지유플러스의 사업 부문은 한국표준산업분류에 의거하여 전기통신업으로 분류되고 있으며 이동 통신 사업, 전화, TPS 등의 사업을 영위하고 있음. 현재 이동통신서비스와 함께 TPS서비스, 전화서비스 및 데이터서비스 등을 영위하고 무선 및 유선 통신사업을 함께 진행하여 시너지 효과 창출을 도모하고 있음. 4G LTE기술의 상용화로 네트워크 전송속도가 발전하여 음성 중심의 시장에서 데이터 중심의 시장으로의 전환이 더욱 가속화되고 있음. 2013년 3분기말 동사의 경우 총 누적가입자 중 LTE 누적 가입자 비중이 약 50%을 차지 할 정도로 LTE 시장이 빠른 성장세를 보이고 있음.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/63857/thumb_39.jpg",
                3.3
            )
        );
        companyList.add(
            createCompany(
                "삼성에스디에스(주) ", "6,801", "삼성SDS는 1985년 설립된 IT 서비스 기업으로 지난 30여 년간 대한민국 IT 산업의 역사를 개척한다는 자부심과 함께 고객의 니즈, 시대의 변화에 따라 끊임없이 진화해 왔습니다.\n" +
                            "전산운영, 시스템통합, IT서비스, 그리고 지금의 ICT 서비스까지 삼성SDS는 IT를 활용한 혁신을 통해 고객의 성장과 발전을 견인해 왔습니다. 또한, 삼성 모든 그룹사의 시스템 운영 및 컨설팅, 시스템 통합, IT 아웃소싱, ICT인프라, ICT 융합 등의 주요 사업을 수행하며 삼성그룹이 글로벌 기업으로 성장하는데 중추 역할을 해왔습니다.\n" +
                            "삼성SDS는 풍부한 경험과 축적된 기술력, 우수한 파트너와 인적 자원을 바탕으로 인프라 구축, 네트워크 서비스, 비즈니스 컨설팅, 제조 IT, 물류 IT, 교육 IT, 의료 IT, Security, Mobility, Cloud, Analytics 등 다양한 ICT 서비스를 제공하며, 세계 수준의 경쟁력을 갖춘 글로벌 ICT 회사로 성장하고 있습니다.\n" +
                            "앞으로도 삼성SDS는 smart 함을 더해 행복한 ICT 세상을 만들어 가는 글로벌 ICT 기업이 되겠습니다.\n",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/30162/thumb_2.jpg",
                3.6
            )
        );
        companyList.add(
            createCompany(
                "(주)엘지씨엔에스", "6,101", "LG CNS는 30년 동안 대한민국을 대표해온 IT서비스 전문기업입니다.\n" +
                            "\n" +
                            "제조, 금융, 공공, 통신, 에너지 등 다양한 분야의 경험에 근거한 산업 전문성과 IT기술력을 바탕으로 \u000B국내외 고객에게 컨설팅, 시스템 구축 및 운영, 아웃소싱 등의 토털 IT서비스를 제공하고 있습니다.\n" +
                            "\n" +
                            "아울러 AI/빅데이터 · 에너지신산업 · 스마트팩토리 · O2O · 헬스케어 등의 분야를 새로운 성장 동력으로 육성하고 있습니다.\u000B이를 위해 글로벌 파트너십과 빅데이터 · IoT · 클라우드 등 신기술의 적극 확보 및 적용으로\u000B새로운 고객 가치를 창출해나가고 있습니다.\n" +
                            "\n" +
                            "LG CNS에서 국내/해외 석박사/MBA 졸업자 및 졸업 예정자를 대상으로 채용을 실시하오니,\u000BIT 전문가 및 컨설턴트로 성장하고 싶으신 열정적이고 진취적인 인재분들의 많은 지원 부탁 드립니다.\n" +
                            "\n" +
                            "감사합니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/63859/thumb_%EC%BA%A1%EC%B2%98.JPG",
                3.2
            )
        );
        companyList.add(
            createCompany(
                "(주)케이티 ", "6,045", "(주)케이티는 1981년 12월 10일에 전기통신사업법에 근거하여 정보통신사업을 영위할 목적으로 전기통신업 전문업체로 설립되었으며 유무선전화, 초고속인터넷 등 통신서비스를 제공함. 동사는 주요 사업으로 전기통신업, 신용카드업, 위성방송서비스, 차량렌탈 사업, 오토리스 및 중고차 할부금융업, 인력공급업, 보안사업, 금융업, 기계장비 및 관련 물품 도매업, 경비 및 경호업 등을 영위. 2013년 3분기 기준 동사의 시장점유율은 가입자수 기준으로 시내전화 82.0%, 이동전화 30.1%, 초고속인터넷 43.2%, TRS 98%를 확보하고 있음. 동사는 연구개발 담당조직으로 전사 CTO기능 수행을 통한 중장기 핵심기술확보 및 사업화 지원",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/53191/thumb_14.png",
                3.2
            )
        );
        companyList.add(
            createCompany(
                "네이버(주)", "6,711", "네이버(주)는 한국 최대 검색포털 네이버 뿐만 아니라, 전 세계 2억 명이 사용하고 있는 모바일 메신저 라인, 동영상 카메라 스노우, 디지털 만화 서비스 네이버웹툰 등을 서비스하고 있는 글로벌 ICT 기업입니다. 네이버는 인공지능, 로보틱스, 모빌리티 등 미래 기술에 대한 지속적인 연구개발을 통해 기술 플랫폼의 변화와 혁신을 추구하며 세계 각국의 수많은 이용자와 다양한 파트너들이 함께 성장할 수 있도록 노력하고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/42217/thumb______.jpg",
                4.0
            )
        );
        companyList.add(
            createCompany(
                "(주)더존비즈온", "4,574", "-",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/81407/thumb_douzone_logo1_jpg.jpg",
                2.4
            )
        );
        companyList.add(
            createCompany(
                "(주)케이티씨에스", "3,474", "대한민국 1등 고객서비스를 제공하는 KT CS는 KT그룹의 고객서비스를 주력사업으로 하는 고객서비스 전문기업입니다.\n" +
                            "KT고객센터, 114번호안내, 컨택센터 아웃소싱, 통신 · 비통신상품 유통, 강사 · 컨설팅 사업, 콕콕114 app 등을 통해 고객 만족 서비스를 제공합니다. 서울과 대전 본사를 비롯해 부산, 광주, 제주 등 7개 지역사업단의 전국적인 네트워크를 보유하고 있으며 9,000여명의 임직원이 종사하고 있습니다. KT CS는 콜센터 산업의 대표기업으로 \"Global No.1 kt cs\"라는 비전 아래 고객 가치 실현과 행복한 삶에 기여하기 위해 항상 최선을 다하고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/53174/thumb_40.jpg",
                2.8
            )
        );
        companyList.add(
            createCompany(
                "효성아이티엑스(주)", "3,724", "효성아이티엑스(주)는 효성그룹의 계열사로서 컨택센터 서비스, CDN(Content Delivery Network; 인터넷 상에서 컨텐츠의 전송을 전문적으로 아웃소싱하여 운영하는 서비스)등 IT 서비스, 영상기기 수입/판매 등 사업 영위. 사업부문은 컨택센터 서비스, CDN서비스, 영상기기 수입/판매 사업으로 구성되어 있음. 컨택센터 시장은 과거 20~30%의 고성장으로 활황을 구가하였으나 2006년 이후 연 8% 정도의 안정적인 성장세를 보이고 있으며, 일반 기업 및 공공, 금융부문에서의 대민 서비스 향상 기조에 따른 시장 확대 기대",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/285/thumb_%ED%9A%A8%EC%84%B1ITX-CI_100_80_0_20.png",
                2.8
            )
        );

        companyList.add(
            createCompany(
                "롯데이노베이트(주)", "5,451", "-",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/35657/thumb_35657.jpg",
                2.8
            )
        );
        companyList.add(
            createCompany(
                "(주)엔씨소프트", "6,185", "(주)엔씨소프트의 주된 사업은 온라인 게임의 개발 및 서비스 제공을 포함한 디지털 엔터테인먼트 관련 인터넷 사업이며 본사를 거점으로 총 16개의 계열회사로 구성되어 있음. 핵심 경쟁력은 MMORPG에 특화된 세계적 수준의 게임 개발 능력이며, 자체 게임 개발 역량 강화를 통해 성장함. 4년동안 130여명의 개발 인력과 230억원의 개발비가 투입된 아이온은 2008년 개봉 이후 지속적으로 국내 PC방 기준 점유율 1위를 유지하였으며, 리지니와 리니지2 또한 국내 시장 점유율 10위권을 유지함. 길드워2는 2013년 하반기 중국 시장에서 출시를 목표로 KongZhong사와 현지 퍼블리싱 계약을 체결하였음.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/64418/thumb_64418.png",
                3.6
            )
        );
        companyList.add(
            createCompany(
                "요기요", "5.098", "지난 10년간 맛있는 즐거움의 의미와 경험을 제공하며, 누구나 원하는 음식을 편리하게 맛볼 수 있도록 달려왔습니다.\n" +
                            "이제 푸드 딜리버리를 넘어, 우리 주변과 지역의 모든 것을 연결하는 Commerce Platform으로 진화하려 합니다.\n" +
                            "지금 이 순간에도 성장하고, 발전하며, 혁신을 만들어 가고 있는 요기요는 가장 고객 중심적인 플랫폼을 만들어 사용자의 일상에 변화를 만들고, 편리함과 풍요로움을 전달하고자 합니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/88549/thumb_profile-image_instagram.jpg",
                3.0
            )
        );
        companyList.add(
            createCompany(
                "(주)카카오", "6.281", "-",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/93880/thumb_kakao.jpg",
                3.8
            )
        );
        companyList.add(
            createCompany(
                "카페24(주)", "4,865", "카페24㈜은 1999년에 설립된 글로벌 전자상거래 플랫폼 기업입니다. 창의가 있는 사람이라면 누구나 국내를 넘어 전 세계 시장에서 온라인 비즈니스가 가능할 수 있도록 쇼핑몰 솔루션, 광고∙마케팅, 호스팅 인프라 등 다양한 서비스를 원스톱 (One-Stop)으로 제공하고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/23717/thumb_001_%EC%B9%B4%ED%8E%9824_%ED%88%AC%EB%AA%85%EB%B0%B0%EA%B2%BD.png",
                3.4
            )
        );
        companyList.add(
            createCompany(
                "(주)야놀자", "5,012", "야놀자는 데이터와 사람으로부터 탄생한 초연결된 여행 기술을 통해 여행을 10배 더 쉽게 한다(10X)는 비전 하에 여행ㆍ여가 산업을 혁신하는 글로벌 선도 여행 데이터 기업입니다.\n",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/89637/thumb_favicon.png",
                2.8
            )
        );
        companyList.add(
            createCompany(
                "씨제이올리브네트웍스(주)", "5,508", "CJ올리브네트웍스는 1995년 창립이래 제조, 유통, 물류, 미디어 등 생활∙문화 기반의 IT서비스를 성공적으로 제공함으로써 고객 만족과 가치를 실현하고 있으며, 고객의 신뢰와 지지를 바탕으로 견실하고 안정적인 성장을 지속해오고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/94863/thumb_ONS.png",
                3.0
            )
        );
        companyList.add(
            createCompany(
                "(주)지마켓", "6,703", "G마켓은 구성원들이 성장할 다양한 기회와 새로운 가능성을 끊임없이 제공합니다.\n" +
                            "이를 통해서 모든 구성원들은 치열하게 고민하고 과감하게 도전하며, 우리가 가진 상상을 현실로 만들어가고 있습니다.\n" +
                            "G마켓은 무한한 가능성의 공간에서 모두가 꿈꾸는 커머스 플랫폼의 미래를 함께 만들어갈 여러분의 지원을 기다립니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/58985/thumb_Gmarket_G_Logo_RGB.png",
                3.7
            )
        );
        companyList.add(
            createCompany(
                "(주)넥슨코리아", "5,896", "1994년 설립, 올해로 창립 30주년을 맞이한 (주)넥슨은 연 매출 3조 9천억원,(2023년 말 넥슨 일본법인 연결매출 기준) 사원수 3,500명 규모의 글로벌 게임회사 입니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/83498/thumb_%EA%B7%B8%EB%A6%BC5.png",
                3.8
            )
        );
        companyList.add(
            createCompany(
                "에스케이플래닛(주)", "6,353", "-",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/20564/thumb___________.png",
                3.5
            )
        );

        companyList.add(
            createCompany(
                "SK텔레콤(주)", "7,592", "SK텔레콤은 유·무선 통신 인프라와 AI 및 ICT 경쟁력을 기반으로 고객에게 차별적인 서비스를 제공하는\n" +
                            "고객·기술·서비스 중심의 「AI & Digital Infra 서비스 컴퍼니」입니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/20575/thumb_27.jpg",
                4.0
            )
        );
        companyList.add(
            createCompany(
                "(주)안랩", "5,000", "(주)안랩은 글로벌 통합보안 기업으로서 세계수준의 기술력으로 개발한 솔루션과 전문적인 서비스 체계를 갖추고, 컨설팅-솔루션-관제 등 시큐리티 라이프 사이클 상의 기술과 서비스를 자체 역량으로 제공하는 국내 유일의 통합보안업체임. 1995년 국내 최초로 안티바이러스 솔루션을 개발, 출시하였으며, 창립 이후 국내 안티바이러스 시장에서 부동의 시장점유율 1위 업체로서의 지위를 유지하여 왔음. 계열회사를 통하여 일본, 중국의 보안사업 및 소셜게임 서비스를 시행하고 있음.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/67099/thumb______.png",
                3.5
            )
        );
        companyList.add(
            createCompany(
                "(주)인라이플", "4,292", "인라이플은 빅데이터 및 AI를 기반으로 광고 솔루션을 제공하는 Ad Tech 기업입니다.\n" +
                            "10여 년간 연구를 통해 AI 엔진을 개발했으며, 연간 44조원 규모의 거래 데이터를 보유하고 있어 광고시장에서는 높은 경쟁력을 갖추고 있습니다.\n" +
                            "모두가 인라이플의 행보를 주목하고 있는 지금, 이제 인라이플은 국내를 넘어 글로벌 리더로 도약하는 한걸음 한걸음을 힘차게 내딛고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/96821/thumb_enliple_%ED%81%B0%EB%B2%84%EC%A0%842.png",
                2.7
            )
        );
        companyList.add(
            createCompany(
                "(주)사람인", "4,824", "(주)사람인에이치알은 2005년 10월 07일 설립되었으며, 온라인 기반의 HR서비스를 주요사업으로 영위하고 있음. 2012년 02월 21일자로 상장되어 코스닥시장에서 매매가 개시됨. HR서비스 제공을 주요 사업으로 영위하고 있으며 사업의 내용은 온라인 기반의 취업포탈 사업부와 오프라인 기반의 HR사업을 영위하는 오프라인 사업부로 구성되어 있음. 온라인 사업부는 인터넷 산업의 특성상 대규모 시설장치와 원/부재료 등의 생산비용을 수반하지 않고 지역의 제약이 없는 영업을 할 수 있으며, 투입된 자본대비 고 부가가치 창출이 가능",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/74164/thumb_saramin2_20180903%20_%EC%9E%A1%ED%94%8C%EB%9E%98%EB%8B%9B_.png",
                3.0
            )
        );
        companyList.add(
            createCompany(
                "(주)케이티아이에스", "3,514", "(주)케이티스는 KT기업집단에 소속된 회사로서, 컨택센터, 광고서비스, 상품판매 사업 및 통신유통 등의 사업을 영위함. 컨텍센터 부문은 특수번호 컨택센터, 운영대행, 인력파견 사업 등 일반 컨택센터 사업, 광고서비스 상품판매 사업은 114 부가서비스 상품판매로서 114우선번호서비스, 114Biz서비스(SMS/MMS), 114DB판매. 통신유통사업은 kt mobile 유통, kt 선불카드 유통, 다문화복합매장 운영, 홈/모바일플라자의kt 면대면 고객접점서비스, 올레플라자(홈/모바일플라자 내 SIS)에서 kt 상품 위탁 판매 등을 함. 신규사업으로 KT Tourist Reward(부가세 환급)사업 추진.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/53178/thumb_22.jpg",
                2.7
            )
        );
        companyList.add(
            createCompany(
                "(주)우아한형제들", "5,837", "우아한형제들은 1등 배달앱 배달의민족으로 음식 문화를 선도하고 있습니다. ‘배민배달’로 배달을 더 빠르고 더 알뜰하게, ‘B마트‘로 온라인 장보기도 즉시 배달하고, '배민스토어'를 통해 세상 모든 상품을 문 앞으로 바로 배달할 날을 꿈꿉니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/61420/thumb_woo_1_.png",
                3.8
            )
        );
        companyList.add(
            createCompany(
                "한국아이비엠(주)", "5,941", "한국아이비엠(주)는 1967년에 설립된 회사로 사원수 2400명 규모의 외국계(외국 투자기업). 주요사업으로는 전산기및전산기부분품수출 전산기임대및판매사업 등이 있음. 2000년 e-비즈니스 호스팅 사업을 시작하였으며, 시스코와 공동영업을 위한 전략적 제휴를 맺음. ISO9001 ITMS 530956(IT service management system), ISO9001 82346.2.1(하드웨어, 기업컨설팅 서비스 운영등의 설계, 개발 및 판매)인증 등을 획득. 본사 서울 강남구 도곡동 소재",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/3386/thumb__________.png",
                3.2
            )
        );
        companyList.add(
            createCompany(
                "(주)인피닉", "3,040", "인피닉은 인공지능 데이터와 SW품질검증 사업을 영위하고 있습니다.\n" +
                            "인공지능 데이터 사업은 인공지능 개발, 검증 및 고도화를 위해 요구되는 데이터셋과 관련된 서비스를 제공하는 사업으로, 비정형데이터를 인공지능 학습이 가능한 형태로 변환하여 정규화시키는 데이터 서비스와 데이터를 축적, 연동하여 활용하는 플랫폼 서비스를 제공합니다.\n" +
                            "SW 품질검증은 제품 및 서비스의 운영을 위해 사용되는 소프트웨어의 안정성, 무결성, 사용성을 개발자가 아닌 제 3자에 의해 평가하여 소프트웨어의 객관적인 품질을 확보하고 이용자의 편의성을 높이기 위한 품질 프로세스입니다. 당사는 국내에서 최초로 SW품질검증사업을 위해 설립된 1세대 품질검증기업의 하나로 자율주행, 전장, 모바일, 통신, 임베디드 등의 분야에 서비스를 제공하고 있습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/57685/thumb_%EC%9E%A1%ED%94%8C%EB%A0%88%EB%8B%9B_%EB%A1%9C%EA%B3%A0.png",
                2.2
            )
        );
        companyList.add(
            createCompany(
                "현대오토에버(주)", "6,082", "현대오토에버는 현대자동차 그룹의 IT 전문 계열사로서 2000년 4월 설립하여, 그룹의 주력사업인 자동차, 부품, 철강, 물류, 중공업, 건설, 금융 분야 등에서 축적된 IT서비스 노하우와 선진 기술력을 바탕으로 지속적으로 성장해왔습니다.\n" +
                            "현대오토에버는 ‘고객 가치 증대를 위한 IT서비스 기업’이라는 이념 아래 고객기업의 정보 선진화를 제 일의 핵심가치로 여기며, 최고의 정보 기술과 전문가를 육성하고 있습니다. 그리고, 전문 IT서비스 뿐만 아니라 제조 IT 및 엔지니어링 서비스, 임베디드 S/W개발, 이러닝 등의 분야에서 최고 수준의 기술력을 바탕으로 차별화된 서비스를 제공하고 있습니다.\n" +
                            "또한, 그룹내 IT 질적 향상을 위해 정보기술 표준화 및 통합화를 구현하여 업무의 효율성을 향상시키고, 품질 강화를 통하여 경영전략의 성공적 수행을 가능케 하며, 아울러 이를 통한 고객사의 미래 가치 발굴을 지원하는 최고의 파트너입니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/1306/thumb_1306.png",
                3.5
            )
        );
        companyList.add(
            createCompany(
                "(주)티맥스소프트", "5,273", "티맥스소프트는 2003년 IBM, 오라클 등 글로벌 기업들을 제치고 국내 웹 어플리케이션 서버(WAS) 시장점유율 1위에 올라선 이후, 현재까지 독보적인 1위를 차지하고 있는 ‘제우스(JEUS)’를 비롯해, 데이터베이스 관리 솔루션(TIBERO Data), 애플리케이션 프레임워크(ProFrame), 메인프레임 리호스팅 솔루션(OpenFrame) 등 다수의 경쟁력 있는 솔루션들을 성공적으로 시장에 선보이며 한국을 대표하는 유일한 시스템 소프트웨어 전문기업으로 성장했습니다.",
                5L, "https://jpassets.jobplanet.co.kr/production/uploads/company/logo/50992/thumb_____46.jpg",
                2.8
            )
        );


    }

    private Company createCompany(
            String name, String averageSalary,
            String info, Long recruitCount,
            String logoUrl, Double grade
    ) {
        Company company = Company.builder()
                .name(name)
                .averageSalary(averageSalary)
                .grade(grade)
                .clickCount(0L)
                .info(info)
                .recruitCount(recruitCount)
                .logoUrl(logoUrl)
                .build();
        companyRepository.save(company);
        return company;
    }

}
