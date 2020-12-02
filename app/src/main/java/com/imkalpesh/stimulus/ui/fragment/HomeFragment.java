package com.imkalpesh.stimulus.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.adapers.dashboard.DashboardParentAdapter;
import com.imkalpesh.stimulus.adapers.general.SliderImageAdaper;
import com.imkalpesh.stimulus.baseclasses.BaseFragment;
import com.imkalpesh.stimulus.databinding.FragmentHomeBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.models.DashboardParentModel;
import com.imkalpesh.stimulus.models.LessonModel;
import com.imkalpesh.stimulus.models.QuotesModel;
import com.imkalpesh.stimulus.ui.activity.GridActivity;
import com.imkalpesh.stimulus.ui.activity.LessonListActivity;
import com.imkalpesh.stimulus.ui.activity.QuotesListActivity;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends BaseFragment implements View.OnClickListener,
        CommonActionListener {

    private static final Integer[] IMAGES = {R.drawable.b_two, R.drawable.b_one,R.drawable.b_three};
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private LessonModel lessonModel;
    private ArrayList<QuotesModel> quotesListActivityArrayList;
    private ArrayList<DashboardParentModel> parentItemList;
    private ArrayList<DashboardChildModel> userQuotesArrayList, celebritesArrayList, motivationalQuotesArrayList, biographiesArrayList, lessonsArraylist;
    private DashboardChildModel childModel;
    private String mainTitle = "Celebrities Quotes", title, userTitle;
    private DashboardParentAdapter dashboardParentAdapter;
    private FragmentHomeBinding binding;
    private ArrayList<LessonModel> lessonModelArrayList;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

   /* {
        AppConstants.ISURI = 0;
    }*/

    @Override
    public Activity registerActivity() {
        return getActivity();
    }

    @Override
    public Fragment registerFragment() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareIntertitialAds();
        createAndLoadRewardedAd();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);


        /*if (diffMillis >= (3600000 * 12)) {

            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
            Toast.makeText(getActivity(), "Shown", Toast.LENGTH_SHORT).show();

        } else {
            // too early
            Toast.makeText(getActivity(), "Ads shown After 12", Toast.LENGTH_SHORT).show();
            binding.adView.setVisibility(View.GONE);
            AppConstants.isSaved = true;

        }*/

        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);



/*
         AdView mAdView;mAdView = new AdView(getActivity());
        mAdView.setAdSize(AdSize.SMART_BANNER);
        ((RelativeLayout) binding.adView).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdUnitId(unitID[unitIdArrayPosition]);
        mAdView.loadAd(adRequest);*/


        binding.fabAddQuotes.setOnClickListener(this);
        lessonModel = new LessonModel();
        viewPagerBinding();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        dashboardParentAdapter = new DashboardParentAdapter(getActivity(), ParentItemList(), this);
        binding.rvMain.setLayoutManager(layoutManager);
        binding.rvMain.setAdapter(dashboardParentAdapter);

        return binding.getRoot();
    }


    private void viewPagerBinding() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        binding.pager.setAdapter(new SliderImageAdaper(getActivity(), ImagesArray));

        /*CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);*/
        final float density = getResources().getDisplayMetrics().density;
        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }

                if (currentPage != -1) {
                    binding.pager.setCurrentItem(currentPage++, true);
                }
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        handler.removeCallbacks(Update);
    }

    private ArrayList<DashboardParentModel> ParentItemList() {

        parentItemList = new ArrayList<>();
        parentItemList.add(new DashboardParentModel(getResources().getString(R.string.title_quotes_by_famous_peoples), getCelebritesChild()));
        parentItemList.add(new DashboardParentModel(getResources().getString(R.string.title_top_quotes_topics), getMotivationalChild()));
        parentItemList.add(new DashboardParentModel(getResources().getString(R.string.title_top_biographies), getBiographiesChild()));
        parentItemList.add(new DashboardParentModel(getResources().getString(R.string.title_top_lessons), getLessonsChild()));
        return parentItemList;

    }

    private ArrayList<DashboardChildModel> getBiographiesChild() {
        biographiesArrayList = new ArrayList<>();
        biographiesArrayList.add(new DashboardChildModel("Nikola Tesla", R.drawable.nikol));
        biographiesArrayList.add(new DashboardChildModel("Mother Teresa", R.drawable.mothertera));
        biographiesArrayList.add(new DashboardChildModel("Einstein", R.drawable.img_albert));
        biographiesArrayList.add(new DashboardChildModel("Steve jobs", R.drawable.img_steve));
        biographiesArrayList.add(new DashboardChildModel("Swami vivekananda", R.drawable.img_vivekanada));
        return biographiesArrayList;
    }

    private ArrayList<DashboardChildModel> getMotivationalChild() {
        motivationalQuotesArrayList = new ArrayList<>();
        motivationalQuotesArrayList.add(new DashboardChildModel("Motivational", R.drawable.bg_m));
        motivationalQuotesArrayList.add(new DashboardChildModel("Inspirational", R.drawable.bg_i));
        motivationalQuotesArrayList.add(new DashboardChildModel("Happiness", R.drawable.bg_h));
        motivationalQuotesArrayList.add(new DashboardChildModel("Relationship", R.drawable.bg_r));
        motivationalQuotesArrayList.add(new DashboardChildModel("Success", R.drawable.bg_s));
        return motivationalQuotesArrayList;

    }

    private ArrayList<DashboardChildModel> getCelebritesChild() {
        celebritesArrayList = new ArrayList<>();
        celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_bill_gates), R.drawable.biily, false));
        celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_jeff_bezos), R.drawable.jaff, false));
        celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_naredra_modi), R.drawable.img_modi, false));
        celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_Stephen_Hawking), R.drawable.imgstephen, false));
        celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_ratan_tata), R.drawable.img_tata, false));
        return celebritesArrayList;
    }

    private ArrayList<DashboardChildModel> getLessonsChild() {
        celebritesArrayList = new ArrayList<>();
        celebritesArrayList.add(new DashboardChildModel("Life Goals", R.drawable.bg_ll_l, false));
        celebritesArrayList.add(new DashboardChildModel("Career", R.drawable.bg_ll_c, false));
        celebritesArrayList.add(new DashboardChildModel("Lifestyle", R.drawable.bg_ll_ll, false));
        celebritesArrayList.add(new DashboardChildModel("Valuable", R.drawable.bg_ll_v, false));
        return celebritesArrayList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onInfoClick(int itemPosition) {

    }

    @Override
    public void onEditClick(int itemPosition) {

    }

    @Override
    public void onDeleteClick(int itemPosition) {

    }

    @Override
    public void onChildClick(Object object) {
        DashboardChildModel childModel = (DashboardChildModel) object;
        Intent intent = new Intent(getActivity(), QuotesListActivity.class);
        Intent intent2 = new Intent(getActivity(), LessonListActivity.class);
        switch (childModel.getChildItemTitle()) {
            case "Bill Gates":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Success is a lousy teacher. It seduces smart people into thinking they can’t lose."));
                quotesListActivityArrayList.add(new QuotesModel("Your most unhappy customers are your greatest source of learning."));
                quotesListActivityArrayList.add(new QuotesModel("Life is not fair — get used to it!"));
                quotesListActivityArrayList.add(new QuotesModel("Patience is a key element of success."));
                quotesListActivityArrayList.add(new QuotesModel("To win big, you sometimes have to take big risks."));
                quotesListActivityArrayList.add(new QuotesModel("If you think your teacher is tough, wait till you get a boss."));
                quotesListActivityArrayList.add(new QuotesModel("As we look ahead into the next century, leaders will be those who empower others."));
                quotesListActivityArrayList.add(new QuotesModel("If you are born poor its not your mistake, But if you die poor its your mistake."));
                quotesListActivityArrayList.add(new QuotesModel("Your most unhappy customers are your greatest source of learning."));
                quotesListActivityArrayList.add(new QuotesModel("It’s fine to celebrate success but it is more important to heed the lessons of failure."));
                quotesListActivityArrayList.add(new QuotesModel("I believe that if you show people the problems and you show them the solutions they will be moved to act."));
                quotesListActivityArrayList.add(new QuotesModel("Measuring programming progress by lines of code is like measuring aircraft building progress by weight."));
                quotesListActivityArrayList.add(new QuotesModel("Don’t compare yourself with anyone in this world. If you do so, you are insulting yourself."));
                quotesListActivityArrayList.add(new QuotesModel("We are not even close to finishing the basic dream of what the PC can be."));
                quotesListActivityArrayList.add(new QuotesModel("The great thing about a computer notebook is that no matter how much you stuff into it, it doesn’t get bigger or heavier."));
                quotesListActivityArrayList.add(new QuotesModel("When you want to do your homework, fill out your tax return, or see all the choices for a trip you want to take, you need a full-size screen."));
                quotesListActivityArrayList.add(new QuotesModel("The advance of technology is based on making it fit in so that you don’t really even notice it, so it’s part of everyday life."));
                quotesListActivityArrayList.add(new QuotesModel("If you show people the problems and you show people the solutions, they will be moved to act."));
                quotesListActivityArrayList.add(new QuotesModel("You will NOT make $60,000 a year right out of high school. You won’t be a vice-president with a car phone until you earn both."));
                quotesListActivityArrayList.add(new QuotesModel("Capitalism is this wonderful thing that motivates people, it causes wonderful inventions to be done."));
                quotesListActivityArrayList.add(new QuotesModel("I believe in innovation and that the way you get innovation is you fund research and you learn the basic facts."));
                quotesListActivityArrayList.add(new QuotesModel("We've got to put a lot of money into changing behavior."));
                quotesListActivityArrayList.add(new QuotesModel("We all need people who will give us feedback. That's how we improve."));
                quotesListActivityArrayList.add(new QuotesModel("Your attitude, not your aptitude, will determine your altitude."));

                break;
            case "Jeff Bezos":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("If you double the number of experiments you do per year you’re going to double your inventiveness."));
                quotesListActivityArrayList.add(new QuotesModel("Life’s too short to hang out with people who aren’t resourceful."));
                quotesListActivityArrayList.add(new QuotesModel("A brand for a company is like a reputation for a person. You earn reputation by trying to do hard things well."));
                quotesListActivityArrayList.add(new QuotesModel("One of the only ways to get out of a tight box is to invent your way out."));
                quotesListActivityArrayList.add(new QuotesModel("The great thing about fact-based decisions is that they overrule the hierarchy.."));
                quotesListActivityArrayList.add(new QuotesModel("Part of company culture is path-dependent – it’s the lessons you learn along the way."));
                quotesListActivityArrayList.add(new QuotesModel("The common question that gets asked in business is, ‘why?’ That’s a good question, but an equally valid question is, ‘why not?."));
                quotesListActivityArrayList.add(new QuotesModel("I’m skeptical of any mission that has advertisers at its centerpiece."));
                quotesListActivityArrayList.add(new QuotesModel("In the old world, you devoted 30% of your time to building a great service and 70% of your time to shouting about it. In the new world, that inverts."));
                quotesListActivityArrayList.add(new QuotesModel("My own view is that every company requires a long-term view."));
                quotesListActivityArrayList.add(new QuotesModel("Cultures, for better or worse, are very stable."));
                quotesListActivityArrayList.add(new QuotesModel("Life's too short to hang out with people who aren't resourceful"));
                quotesListActivityArrayList.add(new QuotesModel("If you only do things where you know the answer in advance, your company goes away."));
                quotesListActivityArrayList.add(new QuotesModel("What consumerism really is, at its worst is getting people to buy things that don't actually improve their lives."));
                quotesListActivityArrayList.add(new QuotesModel("The human brain is an incredible pattern-matching machine."));
                quotesListActivityArrayList.add(new QuotesModel("It's not an experiment if you know it's going to work."));
                quotesListActivityArrayList.add(new QuotesModel("The human brain is an incredible pattern-matching machine."));
                quotesListActivityArrayList.add(new QuotesModel("Our motto at Blue Origin is 'Gradatim Ferociter': 'Step by Step, Ferociously.'"));
                quotesListActivityArrayList.add(new QuotesModel("A company shouldn't get addicted to being shiny, because shiny doesn't last."));
                quotesListActivityArrayList.add(new QuotesModel("Part of company culture is path-dependent - it's the lessons you learn along the way."));
                quotesListActivityArrayList.add(new QuotesModel("I think that, ah, I'm a very goofy sort of person in many ways."));
                quotesListActivityArrayList.add(new QuotesModel("You want your customers to value your service."));
                quotesListActivityArrayList.add(new QuotesModel("Strip malls are history."));
                quotesListActivityArrayList.add(new QuotesModel("My view is there's no bad time to innovate."));
                quotesListActivityArrayList.add(new QuotesModel("I don't want to use my creative energy on somebody else's user interface."));
                quotesListActivityArrayList.add(new QuotesModel("Infrastructure web services had to happen."));
                break;

            case "Stephen Hawking":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Intelligence is the ability to adapt to change."));
                quotesListActivityArrayList.add(new QuotesModel("We are all now connected by the Internet, like neurons in a giant brain."));
                quotesListActivityArrayList.add(new QuotesModel("There could be shadow galaxies, shadow stars, and even shadow people."));
                quotesListActivityArrayList.add(new QuotesModel("Someone told me that each equation I included in the book would halve the sales."));
                quotesListActivityArrayList.add(new QuotesModel("Life would be tragic if it weren't funny."));
                quotesListActivityArrayList.add(new QuotesModel("My goal is simple. It is a complete understanding of the universe, why it is as it is and why it exists at all."));
                quotesListActivityArrayList.add(new QuotesModel("We are all now connected by the Internet, like neurons in a giant brain."));
                quotesListActivityArrayList.add(new QuotesModel("When one's expectations are reduced to zero, one really appreciates everything one does have."));
                quotesListActivityArrayList.add(new QuotesModel("I am just a child who has never grown up. I still keep asking these 'how' and 'why' questions. Occasionally, I find an answer."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing cannot exist forever."));
                quotesListActivityArrayList.add(new QuotesModel("To confine our attention to terrestrial matters would be to limit the human spirit."));
                quotesListActivityArrayList.add(new QuotesModel("Because there is a law such as gravity, the universe can and will create itself from nothing."));
                quotesListActivityArrayList.add(new QuotesModel("I believe there are no questions that science can't answer about a physical universe."));
                quotesListActivityArrayList.add(new QuotesModel("Time travel was once considered scientific heresy, and I used to avoid talking about it for fear of being labelled a 'crank'."));
                quotesListActivityArrayList.add(new QuotesModel("If you believe in science, like I do, you believe that there are certain laws that are always obeyed."));
                quotesListActivityArrayList.add(new QuotesModel("We should seek the greatest value of our action."));
                quotesListActivityArrayList.add(new QuotesModel("There are too many accidents that can befall life on a single planet."));
                quotesListActivityArrayList.add(new QuotesModel("I grew up thinking that a research scientist was a natural thing to be."));
                quotesListActivityArrayList.add(new QuotesModel("I don't think the human race will survive the next thousand years, unless we spread into space."));
                quotesListActivityArrayList.add(new QuotesModel("It is not clear that intelligence has any long-term survival value."));

            case "Naredra Modi":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Hard work never brings fatigue. It brings satisfaction."));
                quotesListActivityArrayList.add(new QuotesModel("Once we decide we have to do something, we can go miles ahead."));
                quotesListActivityArrayList.add(new QuotesModel("We should remain students for lifetime. You should be ready and yearn to learn from every moment of life."));
                quotesListActivityArrayList.add(new QuotesModel("IT + IT + IT; Indian Talent + Information Technology = India Tomorrow."));
                quotesListActivityArrayList.add(new QuotesModel("This country has not been made by politicians, kings or governments. It has been made by farmers, labourers, our mothers and sisters, and youth."));
                quotesListActivityArrayList.add(new QuotesModel("The 21st century is an era of knowledge. If poverty is to be abolished in this century it can be abolished only through knowledge."));
                quotesListActivityArrayList.add(new QuotesModel("My experience in Gujarat shows that howsoever big a problem might be, it is not insurmountable if we have the will to act."));
                quotesListActivityArrayList.add(new QuotesModel("We do not need ‘act’ but action."));
                quotesListActivityArrayList.add(new QuotesModel("Our nation is rich and our people can be rich."));
                quotesListActivityArrayList.add(new QuotesModel("If you call yourself a leader, then you have to be decisive. If you’re decisive, then you have the chance to be a leader. These are two sides to the same coin."));
                quotesListActivityArrayList.add(new QuotesModel("I am a very optimistic man and only an optimistic man can bring optimism in the country."));
                quotesListActivityArrayList.add(new QuotesModel("Our constitution is a ray of hope: H for Harmony, O for Opportunity, P for People’s Participation, and E for Equality."));
                quotesListActivityArrayList.add(new QuotesModel("People’s blessings give you the power to work tirelessly. The only thing required is commitment."));
                quotesListActivityArrayList.add(new QuotesModel("Unless and until you inspire the people, you will not get results. Imposition will never give you the results. Inspiration will always give you the results."));
                quotesListActivityArrayList.add(new QuotesModel("Today the world looks at India with hope and confidence."));
                quotesListActivityArrayList.add(new QuotesModel("India must take the lead in cybersecurity through innovation."));
                quotesListActivityArrayList.add(new QuotesModel("Education for each child should be the number one priority. Families should realize that houses and cars are much less important than a child’s education."));
                quotesListActivityArrayList.add(new QuotesModel("By getting an opportunity to serve society, we get a chance to repay our debt."));
                quotesListActivityArrayList.add(new QuotesModel("Individual efforts can bring excellence but only collective efforts can deliver effectively."));
                quotesListActivityArrayList.add(new QuotesModel("Truth will ultimately prevail."));
                quotesListActivityArrayList.add(new QuotesModel("I want my nation to be modified as a secure prosperous and dignified nation. Do you?."));
                quotesListActivityArrayList.add(new QuotesModel("I am a small man who wants to do big things for small people."));
                quotesListActivityArrayList.add(new QuotesModel("Don’t dream to be something but rather dream to do something great."));
                quotesListActivityArrayList.add(new QuotesModel("Dreams are not seen when you sleep, dreams are those that don’t let you sleep."));
                quotesListActivityArrayList.add(new QuotesModel("Education makes life self-reliant. It inspires man to live with dignity in the society."));
                break;

            case "Ratan Tata":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Challenges need to be given to an organization."));
                quotesListActivityArrayList.add(new QuotesModel("The day I am not able to fly will be a sad day for me."));
                quotesListActivityArrayList.add(new QuotesModel("Power and wealth are not two of my main stakes."));
                quotesListActivityArrayList.add(new QuotesModel("I've often felt that the Indian tiger has not been unleashed."));
                quotesListActivityArrayList.add(new QuotesModel("If it stands the test of public scrutiny, do it... if it doesn't stand the test of public scrutiny then don't do it."));
                quotesListActivityArrayList.add(new QuotesModel("I would say that one of the things I wish I could do differently would be to be more outgoing."));
                quotesListActivityArrayList.add(new QuotesModel("Banana republics are run on cronyism."));
                quotesListActivityArrayList.add(new QuotesModel("Jardine is the largest dealer of Mercedes in the world. They also sell cars for two or three Japanese makers."));
                quotesListActivityArrayList.add(new QuotesModel("As you grow older, you become - everybody becomes - less inflexible and a little more accommodating."));
                quotesListActivityArrayList.add(new QuotesModel("The most expensive part of any manufacturing unit is the paint shop."));
                quotesListActivityArrayList.add(new QuotesModel("People still believe what they read is necessarily the truth."));
                quotesListActivityArrayList.add(new QuotesModel("I am interested in what I earn; I am interested in my growth."));
                quotesListActivityArrayList.add(new QuotesModel("I would say that I'm blessed with a very, very good executive team that operates, reasonably autonomously, each of the companies."));
                quotesListActivityArrayList.add(new QuotesModel("I buy a lot of electronics, some which I never take out of the box!"));
                quotesListActivityArrayList.add(new QuotesModel("India needs to come out of its socialist pattern of doing things on a rationing basis."));
                quotesListActivityArrayList.add(new QuotesModel("There has not been a conscious view of re-energising manufacturing. So, in some form, someone has to wave the Union Jack in the area of manufacturing."));
                quotesListActivityArrayList.add(new QuotesModel("I have two or three cars that I like, but today, Ferrari would be the best car I have driven in terms of being an impressive car."));
                quotesListActivityArrayList.add(new QuotesModel("I think there are many honest businessmen."));
                quotesListActivityArrayList.add(new QuotesModel("I probably have everything that Apple has made and everything Bose has made; I am very loyal to certain brands."));
                quotesListActivityArrayList.add(new QuotesModel("Fiat has assembly plants in Brazil and Argentina."));
                quotesListActivityArrayList.add(new QuotesModel("The early Rockefellers made their wealth from being in certain businesses and remained personally very wealthy."));
                quotesListActivityArrayList.add(new QuotesModel("There's a lot of interest in Nano outside India."));
                quotesListActivityArrayList.add(new QuotesModel("When you have to earmark human and monetary resources for such a long time, it starts to hinder your other activities."));
                quotesListActivityArrayList.add(new QuotesModel("A founder who is in for the short run, or has no passion for the sector he is in, doesn't give me a great deal of comfort."));
                quotesListActivityArrayList.add(new QuotesModel("When you have to earmark human and monetary resources for such a long time, it starts to hinder your other activities."));
                break;


            case "Motivational":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Life is 10% what happens to you and 90% how you react to it."));
                quotesListActivityArrayList.add(new QuotesModel("It always seems impossible until it's done."));
                quotesListActivityArrayList.add(new QuotesModel("Good, better, best. Never let it rest. 'Til your good is better and your better is best."));
                quotesListActivityArrayList.add(new QuotesModel("If you can dream it, you can do it."));
                quotesListActivityArrayList.add(new QuotesModel("Be kind whenever possible. It is always possible."));
                quotesListActivityArrayList.add(new QuotesModel("When something is important enough, you do it even if the odds are not in your favor."));
                quotesListActivityArrayList.add(new QuotesModel("Start where you are. Use what you have. Do what you can."));
                quotesListActivityArrayList.add(new QuotesModel("Failure will never overtake me if my determination to succeed is strong enough."));
                quotesListActivityArrayList.add(new QuotesModel("Aim for the moon. If you miss, you may hit a star."));
                quotesListActivityArrayList.add(new QuotesModel("No bird soars too high if he soars with his own wings."));
                quotesListActivityArrayList.add(new QuotesModel("Motivation is the art of getting people to do what you want them to do because they want to do it."));
                quotesListActivityArrayList.add(new QuotesModel("Only I can change my life. No one can do it for me."));
                quotesListActivityArrayList.add(new QuotesModel("Problems are not stop signs, they are guidelines."));
                quotesListActivityArrayList.add(new QuotesModel("I am not afraid... I was born to do this."));
                quotesListActivityArrayList.add(new QuotesModel("Well done is better than well said."));
                quotesListActivityArrayList.add(new QuotesModel("Well done is better than well said."));
                quotesListActivityArrayList.add(new QuotesModel("If you're going through hell, keep going."));
                quotesListActivityArrayList.add(new QuotesModel("Don't watch the clock; do what it does. Keep going."));
                quotesListActivityArrayList.add(new QuotesModel("Well done is better than well said."));
                quotesListActivityArrayList.add(new QuotesModel("By failing to prepare, you are preparing to fail."));
                quotesListActivityArrayList.add(new QuotesModel("Perseverance is not a long race; it is many short races one after the other."));
                quotesListActivityArrayList.add(new QuotesModel("He conquers who endures."));
                quotesListActivityArrayList.add(new QuotesModel("Keep your eyes on the stars, and your feet on the ground."));
                quotesListActivityArrayList.add(new QuotesModel("Every exit is an entry somewhere else."));
                quotesListActivityArrayList.add(new QuotesModel("Act as if what you do makes a difference. It does."));
                quotesListActivityArrayList.add(new QuotesModel("Accept the challenges so that you can feel the exhilaration of victory."));
                quotesListActivityArrayList.add(new QuotesModel("Opportunity does not knock, it presents itself when you beat down the door."));
                quotesListActivityArrayList.add(new QuotesModel("Go for it now. The future is promised to no one."));
                quotesListActivityArrayList.add(new QuotesModel("I attribute my success to this - I never gave or took any excuse."));
                quotesListActivityArrayList.add(new QuotesModel("The way to get started is to quit talking and begin doing."));
                quotesListActivityArrayList.add(new QuotesModel("Follow your inner moonlight; don't hide the madness."));
                quotesListActivityArrayList.add(new QuotesModel("Without hard work, nothing grows but weeds."));
                quotesListActivityArrayList.add(new QuotesModel("Always do your best. What you plant now, you will harvest later."));
                quotesListActivityArrayList.add(new QuotesModel("The ultimate aim of the ego is not to see something, but to be something."));
                quotesListActivityArrayList.add(new QuotesModel("The most effective way to do it, is to do it."));
                quotesListActivityArrayList.add(new QuotesModel("A creative man is motivated by the desire to achieve, not by the desire to beat others."));
                quotesListActivityArrayList.add(new QuotesModel("Don't think, just do."));
                quotesListActivityArrayList.add(new QuotesModel("Either you run the day or the day runs you."));
                quotesListActivityArrayList.add(new QuotesModel("Always desire to learn something useful."));
                quotesListActivityArrayList.add(new QuotesModel("Things do not happen. Things are made to happen."));
                break;

            case "Inspirational":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("What lies behind you and what lies in front of you, pales in comparison to what lies inside of you."));
                quotesListActivityArrayList.add(new QuotesModel("I can't change the direction of the wind, but I can adjust my sails to always reach my destination."));
                quotesListActivityArrayList.add(new QuotesModel("We know what we are, but know not what we may be."));
                quotesListActivityArrayList.add(new QuotesModel("Don't judge each day by the harvest you reap but by the seeds that you plant."));
                quotesListActivityArrayList.add(new QuotesModel("If opportunity doesn't knock, build a door."));
                quotesListActivityArrayList.add(new QuotesModel("A champion is someone who gets up when he can't."));
                quotesListActivityArrayList.add(new QuotesModel("I hated every minute of training, but I said, 'Don't quit. Suffer now and live the rest of your life as a champion.'"));
                quotesListActivityArrayList.add(new QuotesModel("No act of kindness, no matter how small, is ever wasted."));
                quotesListActivityArrayList.add(new QuotesModel("Clouds come floating into my life, no longer to carry rain or usher storm, but to add color to my sunset sky."));
                quotesListActivityArrayList.add(new QuotesModel("We must let go of the life we have planned, so as to accept the one that is waiting for us."));
                quotesListActivityArrayList.add(new QuotesModel("The best preparation for tomorrow is doing your best today."));
                quotesListActivityArrayList.add(new QuotesModel("The best preparation for tomorrow is doing your best today."));
                quotesListActivityArrayList.add(new QuotesModel("There are two ways of spreading light: to be the candle or the mirror that reflects it."));
                quotesListActivityArrayList.add(new QuotesModel("Change your thoughts and you change your world."));
                quotesListActivityArrayList.add(new QuotesModel("I believe every human has a finite number of heartbeats. I don't intend to waste any of mine."));
                quotesListActivityArrayList.add(new QuotesModel("God sleeps in the minerals, awakens in plants, walks in animals, and thinks in man."));
                quotesListActivityArrayList.add(new QuotesModel("Let us remember: One book, one pen, one child, and one teacher can change the world."));
                quotesListActivityArrayList.add(new QuotesModel("Do your little bit of good where you are; it's those little bits of good put together that overwhelm the world."));
                quotesListActivityArrayList.add(new QuotesModel("Someone is sitting in the shade today because someone planted a tree a long time ago."));
                quotesListActivityArrayList.add(new QuotesModel("Someone is sitting in the shade today because someone planted a tree a long time ago."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing is impossible, the word itself says 'I'm possible'!"));
                quotesListActivityArrayList.add(new QuotesModel("I am not afraid of tomorrow, for I have seen yesterday and I love today!"));
                quotesListActivityArrayList.add(new QuotesModel("The power of imagination makes us infinite."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is not something you postpone for the future; it is something you design for the present."));
                quotesListActivityArrayList.add(new QuotesModel("The best way out is always through."));
                quotesListActivityArrayList.add(new QuotesModel("The most beautiful thing in the world is, of course, the world itself."));
                quotesListActivityArrayList.add(new QuotesModel("What we achieve inwardly will change outer reality."));
                quotesListActivityArrayList.add(new QuotesModel("Courage, my friends; 'tis not too late to build a better world."));
                quotesListActivityArrayList.add(new QuotesModel("A single sunbeam is enough to drive away many shadows."));
                quotesListActivityArrayList.add(new QuotesModel("Courage, my friends; 'tis not too late to build a better world."));
                quotesListActivityArrayList.add(new QuotesModel("We relish news of our heroes, forgetting that we are extraordinary to somebody too."));
                quotesListActivityArrayList.add(new QuotesModel("You change your life by changing your heart."));
                quotesListActivityArrayList.add(new QuotesModel("From a small seed a mighty trunk may grow."));
                quotesListActivityArrayList.add(new QuotesModel("Light tomorrow with today!"));
                quotesListActivityArrayList.add(new QuotesModel("Be brave enough to live life creatively. The creative place where no one else has ever been."));
                quotesListActivityArrayList.add(new QuotesModel("Too low they build, who build beneath the stars."));
                quotesListActivityArrayList.add(new QuotesModel("A #2 pencil and a dream can take you anywhere."));
                break;

            case "Happiness":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Be happy for this moment. This moment is your life."));
                quotesListActivityArrayList.add(new QuotesModel("It is not how much we have, but how much we enjoy, that makes happiness."));
                quotesListActivityArrayList.add(new QuotesModel("Some cause happiness wherever they go; others whenever they go."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness often sneaks in through a door you didn't know you left open."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is a virtue, not its reward."));
                quotesListActivityArrayList.add(new QuotesModel("If you want others to be happy, practice compassion. If you want to be happy, practice compassion."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is a direction, not a place."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is not something ready made. It comes from your own actions."));
                quotesListActivityArrayList.add(new QuotesModel("Action may not always bring happiness; but there is no happiness without action."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness depends upon ourselves."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness can exist only in acceptance."));
                quotesListActivityArrayList.add(new QuotesModel("Our happiness depends on wisdom all the way."));
                quotesListActivityArrayList.add(new QuotesModel("The art of being happy lies in the power of extracting happiness from common things."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is not a matter of intensity but of balance, order, rhythm and harmony."));
                quotesListActivityArrayList.add(new QuotesModel("True happiness consists not in the multitude of friends, but in the worth and choice."));
                quotesListActivityArrayList.add(new QuotesModel("I am a kind of paranoid in reverse. I suspect people of plotting to make me happy."));
                quotesListActivityArrayList.add(new QuotesModel("All who joy would win must share it. Happiness was born a Twin."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is good health and a bad memory."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness seems made to be shared."));
                quotesListActivityArrayList.add(new QuotesModel("True happiness is... to enjoy the present, without anxious dependence upon the future."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is a continuation of happenings which are not resisted."));
                quotesListActivityArrayList.add(new QuotesModel("Lovers who love truly do not write down their happiness."));
                quotesListActivityArrayList.add(new QuotesModel("The only way to find true happiness is to risk being completely cut open."));
                quotesListActivityArrayList.add(new QuotesModel("The Constitution only gives people the right to pursue happiness. You have to catch it yourself."));
                quotesListActivityArrayList.add(new QuotesModel("The most worth-while thing is to try to put happiness into the lives of others."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness grows at our own firesides, and is not to be picked in strangers' gardens."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness isn't something you experience; it's something you remember."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is the harvest of a quiet eye."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness doesn't depend on any external conditions, it is governed by our mental attitude."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is like a kiss. You must share it to enjoy it."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness... consists in giving, and in serving others."));
                break;
            case "Relationship":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("If you love someone, set them free. If they come back they're yours; if they don't they never were."));
                quotesListActivityArrayList.add(new QuotesModel("How people treat you is their karma; how you react is yours."));
                quotesListActivityArrayList.add(new QuotesModel("When someone shows you who they are, believe them the first time."));
                quotesListActivityArrayList.add(new QuotesModel("Truth is everybody is going to hurt you: you just gotta find the ones worth suffering for."));
                quotesListActivityArrayList.add(new QuotesModel("Never trust a husband too far, nor a bachelor too near."));
                quotesListActivityArrayList.add(new QuotesModel("People change and forget to tell each other."));
                quotesListActivityArrayList.add(new QuotesModel("Assumptions are the termites of relationships."));
                quotesListActivityArrayList.add(new QuotesModel("Once a woman has forgiven her man, she must not reheat his sins for breakfast."));
                quotesListActivityArrayList.add(new QuotesModel("There is always some madness in love. But there is also always some reason in madness."));
                quotesListActivityArrayList.add(new QuotesModel("The one who loves the least, controls the relationship."));
                quotesListActivityArrayList.add(new QuotesModel("Ultimately, we wish the joy of perfect union with the person we love."));
                quotesListActivityArrayList.add(new QuotesModel("When a woman is talking to you, listen to what she says with her eyes."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing is perfect. Life is messy. Relationships are complex. Outcomes are uncertain. People are irrational."));
                quotesListActivityArrayList.add(new QuotesModel("In human relationships, kindness and lies are worth a thousand truths.."));
                quotesListActivityArrayList.add(new QuotesModel("Each relationship nurtures a strength or weakness within you."));
                quotesListActivityArrayList.add(new QuotesModel("Falling in love and having a relationship are two different things."));
                quotesListActivityArrayList.add(new QuotesModel("We can improve our relationships with others by leaps and bounds if we become encouragers instead of critics."));
                quotesListActivityArrayList.add(new QuotesModel("Treasure your relationships, not your possessions."));
                quotesListActivityArrayList.add(new QuotesModel("The heart is forever inexperienced."));
                quotesListActivityArrayList.add(new QuotesModel("A healthy relationship is built on unwavering trust."));
                quotesListActivityArrayList.add(new QuotesModel("The heart is forever inexperienced."));
                quotesListActivityArrayList.add(new QuotesModel("It takes one person to forgive, it takes two people to be reunited."));
                quotesListActivityArrayList.add(new QuotesModel("A relationship requires a lot of work and commitment."));
                quotesListActivityArrayList.add(new QuotesModel("Once, I had so many scripts coming to me that I could hardly read them all."));
                quotesListActivityArrayList.add(new QuotesModel("I always knew it was going to be difficult when I got beyong 40, but I didn't realise it would start at 35."));
                break;
            case "Success":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Success seems to be largely a matter of hanging on after others have let go."));
                quotesListActivityArrayList.add(new QuotesModel("Coming together is a beginning; keeping together is progress; working together is success."));
                quotesListActivityArrayList.add(new QuotesModel("Success is the sum of small efforts - repeated day in and day out."));
                quotesListActivityArrayList.add(new QuotesModel("Success is not final, failure is not fatal: it is the courage to continue that counts."));
                quotesListActivityArrayList.add(new QuotesModel("The greatest sign of success for a teacher... is to be able to say, 'The children are now working as if I did not exist.'"));
                quotesListActivityArrayList.add(new QuotesModel("All you need in this life is ignorance and confidence, and then success is sure."));
                quotesListActivityArrayList.add(new QuotesModel("Don't aim for success if you want it; just do what you love and believe in, and it will come naturally."));
                quotesListActivityArrayList.add(new QuotesModel("Strive not to be a success, but rather to be of value."));
                quotesListActivityArrayList.add(new QuotesModel("If you find it in your heart to care for somebody else, you will have succeeded."));
                quotesListActivityArrayList.add(new QuotesModel("One fails forward toward success."));
                quotesListActivityArrayList.add(new QuotesModel("Find something you're passionate about and keep tremendously interested in it."));
                quotesListActivityArrayList.add(new QuotesModel("Defeat is not the worst of failures. Not to have tried is the true failure."));
                quotesListActivityArrayList.add(new QuotesModel("There is only one success - to be able to spend your life in your own way."));
                quotesListActivityArrayList.add(new QuotesModel("Success isn't a result of spontaneous combustion. You must set yourself on fire."));
                quotesListActivityArrayList.add(new QuotesModel("Success is dependent on effort."));
                quotesListActivityArrayList.add(new QuotesModel("No man succeeds without a good woman behind him. Wife or mother, if it is both, he is twice blessed indeed."));
                quotesListActivityArrayList.add(new QuotesModel("A champion is afraid of losing. Everyone else is afraid of winning."));
                quotesListActivityArrayList.add(new QuotesModel("Success breeds success."));
                quotesListActivityArrayList.add(new QuotesModel("Success is dependent on effort."));
                quotesListActivityArrayList.add(new QuotesModel("The successful man will profit from his mistakes and try again in a different way."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is the death of hope."));
                quotesListActivityArrayList.add(new QuotesModel("Success is dependent on effort."));
                quotesListActivityArrayList.add(new QuotesModel("Seventy percent of success in life is showing up."));
                quotesListActivityArrayList.add(new QuotesModel("Tradition is the illusion of permanance."));
                quotesListActivityArrayList.add(new QuotesModel("In my house I'm the boss, my wife is just the decision maker."));
                quotesListActivityArrayList.add(new QuotesModel("Life is divided into the horrible and the miserable."));
                break;

            case "Life Goals":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Never give up.", "Tenacity is the virtue of sticking it out and never throwing in the towel when things start to get tough. Many people who have regrets are those who quit when success was almost within reach because the way was too hard. Just keep going."));
                lessonModelArrayList.add(new LessonModel("Embrace simplicity.", "Practice the art and virtue in all aspects of your life. When you de-clutter and remove the excesses in your life, you will gain more peace and appreciate life more."));
                lessonModelArrayList.add(new LessonModel("Time is your ally. Use it wisely.", "Our time is short. We could die at any time. Live your life like there is no tomorrow and leave no regrets behind."));
                lessonModelArrayList.add(new LessonModel("Beware of excesses.", "There is the line that goes, “Too much of a good thing is bad.” Focus on quality instead of quantity, be it in about the things you own or the activities you do."));
                lessonModelArrayList.add(new LessonModel("You will not always get what you wish for.", "But you will always be given what you need at the right moment. Sometimes, you don’t even you realize you need it, whether it’s a life lesson, a material thing, a person, or an animal."));
                lessonModelArrayList.add(new LessonModel("You can learn a lot from children.", "Compared to adults, kids have an unbiased and open view of the world. They have this ability to put things in perspective in a way that many adults do not have or have forgotten."));
                lessonModelArrayList.add(new LessonModel("Love with all your might.", "To love and be loved in return makes everything worthwhile. So love your family and your friends. Do not take them for granted."));
                lessonModelArrayList.add(new LessonModel("Love with all your might.", "To love and be loved in return makes everything worthwhile. So love your family and your friends. Do not take them for granted."));
                lessonModelArrayList.add(new LessonModel("Failure is the greatest source of wisdom.", "When you fail at something, take a look at what you’ve been doing. Adjust or change your approach until you achieve your objectives."));
                lessonModelArrayList.add(new LessonModel("There is power in silence.", "In conversations, you gain so much by listening more and allowing others to speak their turn."));
                lessonModelArrayList.add(new LessonModel("Express your feelings", "Tell the people you care about how much you love them. If things upset you, learn to assert yourself without being aggressive. There is no positive benefit from bottling up your emotions. On the contrary, it can create health problems in the long run."));
                lessonModelArrayList.add(new LessonModel("Travel is the best education you’ll get.", "You will learn so much – about a culture, a place, and yourself – when you travel. Do it as often as you can. It is the best education you’ll have in the University of Life."));
                lessonModelArrayList.add(new LessonModel("Things become clutter.", "Things you have accumulated today might be cause for annoyance later. Learn how to make mindful purchases and avoid impulse buying."));
                lessonModelArrayList.add(new LessonModel("All of these are temporary.", "Whatever challenges you’re experiencing now are all temporary. They, too, will pass. The same goes for the good times. Prepare your mind to accept change."));
                lessonModelArrayList.add(new LessonModel("Your words have power.", "Always strive to mean what you say. Use your words sparingly and wisely."));
                lessonModelArrayList.add(new LessonModel("Life will hit you hard.", "But the important thing is regardless of how hard you’re hit you keep moving on. Having the ability to endure no matter what challenges are thrown your way is the key to winning in life."));
                childModel.setLessonLayout(1);
                break;

            case "Career":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Success starts from inside out.", "Many driven and successful people concentrate on every aspect of their work life but neglect their health. Being busy, productive, and successful is great, but you need to stay healthy to keep up the pace. Make sure you're getting enough nutrition, exercise, and rest."));
                lessonModelArrayList.add(new LessonModel("In relationships, the little things are the big things.", "The most successful people tend to have the broadest and most diverse social networks. And they aren't just superficial acquaintances, but a broad network of genuine connections. Developing relationships is a very important part of doing business. The more time and effort you put into the little things, the more important they become."));
                lessonModelArrayList.add(new LessonModel("Your social life belongs offline.", "If you spend hours on Facebook, Twitter, or Instagram, checking emails and messages, you're missing out on more than you're gaining. Connecting to the world means spending time with people in real life--speaking to them, interacting with them, and connecting with them."));
                lessonModelArrayList.add(new LessonModel("Success is incremental.", "Overnight success is seriously rare. Most people take years, even decades, to build a great career. The secret is to work hard every single day, and remember that even small wins can boost your life tremendously. Over time, you'll find yourself reaching bigger and bigger goals."));
                lessonModelArrayList.add(new LessonModel("Together is always better.", "Many people would rather go it alone, but success is a team sport. The idea that you can succeed on your own is a myth. Every big idea deserves a team to make it happen."));
                lessonModelArrayList.add(new LessonModel("Failure is never fatal.", "Like it or not, messing up is an essential part of the success process. Failure is never the end--it's the opportunity to do better next time. Those who fail and are brave enough to try again eventually win out."));
                lessonModelArrayList.add(new LessonModel("Don't let silly things rob your happiness.", "So many of us allow the most ridiculous little things to rob us of our happiness. But we can always choose to be happy. Life is a choice; happiness is a choice. No matter what your situation, don't let anything separate you from a sense of joy."));
                lessonModelArrayList.add(new LessonModel("Everyone is winging it", "Some of us have more education, experience, and training, but at the heart of it, we’re all playing the same guessing game."));
                childModel.setLessonLayout(1);
                break;

            case "Lifestyle":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Sitting in dining halls for hours is dangerous.", "We all know socializing is the best part of college, and I have found myself talking to my friends over dinner for hours at a time in the dining halls. Socializing isn't a bad thing, but surrounding yourself with endless food is. I found myself grazing the dessert table several times in one night. Get up and talk in the dorms. Go for a walk. Just get yourself away from the food"));
                lessonModelArrayList.add(new LessonModel("It's ok to snack.", "In theory cutting out snacks saves calories; however, in reality, it cause you to binge at meal times. When I go to the dining halls starving, I eat so fast that I don't even know when I'm full. Snack but snack smart by getting these five snacks every college student needs in their dorm room."));
                lessonModelArrayList.add(new LessonModel("Buy single serving snacks.", "Portion sizes are essential to a good diet, but when you have a family size bag of potatoe chips it's hard to keep track of how much you are really eating. I even try to convince myself that I've only had one serving when really I had three or four. Buying small, prepackaged foods will help you stay on track."));
                lessonModelArrayList.add(new LessonModel("The late night trip to Wawa isn't worth it.", "Trust me I know that everything tastes better when your drunk, but just go to bed. You will thank me in the morning. "));
                lessonModelArrayList.add(new LessonModel("Give A Little of Yourself For Free.", "There's nothing that gives you as big a warm fuzzy feeling as helping out others in need or less fortunate than yourself. Volunteering, even just for a couple of hours a week or a month, really puts life into perspective, which is good for your soul. Many people rely on the kindness of strangers to make their world a better place to live in."));
                lessonModelArrayList.add(new LessonModel("Stop Burning the Candle at Both Ends", "Trying to fit as much into your day as you can leaves little time for sleep.But people who sleep more are generally healthier. Their memories are improved, their problem-solving abilities remain sharp, and they act in a more reasoned way. Research also indicates they are less likely to put on weight or develop type 2 diabetes, and most likely to live to 100."));
                childModel.setLessonLayout(1);
                break;

            case "Valuable":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Serve over everything.", "If you want to grow your business, find ways you can serve more people while also looking for more efficient ways to serve your existing customers. When I started to direct my focus away from all that I could potentially gain to serving more people, my business, impact and life changed drastically. "));
                lessonModelArrayList.add(new LessonModel("The power of a strong, clear and massive vision is incredible.", "If you need a breakthrough, you must first start to fully believe that the future is going to be bigger than the past. I had a lot of short-term disappointments in 2015, and the only way I was able to keep moving forward despite the setbacks was because of the vision that I created."));
                lessonModelArrayList.add(new LessonModel("Persistence always pays off.", "It may not always be when we want it to happen, but if we stay persistent day in and day out, we eventually will get to where we want to go. Staying persistent for most people is extremely difficult, because most people seek instant gratification and praise."));
                lessonModelArrayList.add(new LessonModel("Never settle for average.", "Throughout all of my travels in 2015, the one constant negative trait that continued to pop up was all of the men and women who have reached a settlement with mediocrity. They have stopped expanding, growing, and working to become world-class at what they do. You may never rise to be the absolute best at what it is you do for a living, but never settle for average or just doing enough to get by. I have seen plenty of people as both an athlete and in the business arena with very minimal talent go on to become a peak performer, simply because they were hungry enough to not be average."));
                lessonModelArrayList.add(new LessonModel("Relationships rule.", "At the end of the day, what matters most in life are the people in our lives. Put them first every single day. Before work. Before the computer. Before your hobbies. Treat them like they are your everything. Because they are."));
                lessonModelArrayList.add(new LessonModel("Things gather dust.", "Time and money spent accumulating material things will one day irritate you. You have to clean, maintain, store, and move stuff. The less stuff you have in your life, the freer you are. Purchase mindfully. Simplify. Declutter your life."));
                lessonModelArrayList.add(new LessonModel("Love yourself more of the time.", "So many of us, including myself, look to help and love others way before we even start to pay attention to our own well-being and happiness. This doesn’t mean to boast about yourself -- but to pay more attention to yourself. When we love ourselves more of the time, we have that much more to give to the world, our business and other people."));
                lessonModelArrayList.add(new LessonModel("Failure is good.", "We try so hard to avoid failure, but failure is the real evidence that we've had the courage to try. If you avoid failure, you avoid taking action. Expect and accept that failure is part of the experience. Learn from it, grow from it, and move on."));
                childModel.setLessonLayout(1);
                break;


            case "Nikola Tesla":
                biographiesURI(1, "https://drive.google.com/file/d/1cVGNVMyQo8GdAhTV9vobMA5Sc-WBUFjF/view");
                break;
            case "Mother Teresa":
                biographiesURI(1, "https://drive.google.com/file/d/1sNfftf_IX8g6ts8cAVUTGDQHJlnZWfxD/view?usp=sharing");
                break;
            case "Einstein":
                biographiesURI(1, "https://drive.google.com/file/d/1O6dQwdblmHdp2ALvWcoqF-6xUBiJwYna/view?usp=sharing");
                break;
            case "Steve jobs":
                biographiesURI(1, "https://drive.google.com/file/d/1i4J3P8KgEINmPVCpQIqO_qJZnV11TNuN/view?usp=sharing");
                break;
            case "Swami vivekananda":
                biographiesURI(1, "https://drive.google.com/file/d/1HuvcZgZIzmTf6emvKk0IXjJMf5Js4Fzs/view?usp=sharing");
                break;
        }
        if (AppConstants.ISURI == 0) {
            if (childModel.getLessonLayout() == 1) {
                intent2.putParcelableArrayListExtra(AppConstants.LESSON_ARRAYLIST_NAME, lessonModelArrayList);
                intent2.putExtra(AppConstants.LESSON_NAME, childModel.getChildItemTitle());
                startActivity(intent2);
                lessonModelArrayList.clear();
                childModel.setLessonLayout(0);
            } else {
                intent.putParcelableArrayListExtra(AppConstants.QUOTES_ARRAYLIST_NAME, quotesListActivityArrayList);
                intent.putExtra(AppConstants.QUOTES_AUTHOR_NAME, childModel.getChildItemTitle());
                startActivity(intent);
            }
        }
        AppConstants.ISURI = 0;
    }

    private void biographiesURI(int i, String uri) {
        if (i == 1) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            AppConstants.ISURI = 1;
        } else {
            AppConstants.ISURI = 0;
        }

    }

    @Override
    public void onParentClick(Object object) {
        DashboardParentModel dashboardParentModel = (DashboardParentModel) object;
        if (dashboardParentModel.getParentItemTitle().equals(getResources().getString(R.string.title_quotes_by_famous_peoples))) {
            celebritesArrayList.clear();
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_bill_gates), R.drawable.biily, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_jeff_bezos), R.drawable.jaff, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_naredra_modi), R.drawable.img_modi, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_Stephen_Hawking), R.drawable.imgstephen, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_ratan_tata), R.drawable.img_tata, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_br_ambedkar), R.drawable.img_saheb, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_swami_vivekananda), R.drawable.img_vivekanada, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_obama), R.drawable.img_obama, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_albert_einstein), R.drawable.img_albert, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_abdul_kalam), R.drawable.img_kalam, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_Sundar_Pichai), R.drawable.img_sundar, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_jack_ma), R.drawable.imgjackma, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_chanakya), R.drawable.img_chankya, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_satya_nadela), R.drawable.nadela, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_steve_jobs), R.drawable.img_steve, false));
            celebritesArrayList.add(new DashboardChildModel(getResources().getString(R.string.cel_elon_musk), R.drawable.img_elon, false));
            celebritesArrayList.add(new DashboardChildModel("Coming Soon",R.drawable.coming_soon));
        } else if (dashboardParentModel.getParentItemTitle().equals(getResources().getString(R.string.title_top_quotes_topics))) {
            celebritesArrayList.clear();
            celebritesArrayList.add(new DashboardChildModel("Motivational", R.drawable.bg_m, false));
            celebritesArrayList.add(new DashboardChildModel("Inspirational", R.drawable.bg_i, false));
            celebritesArrayList.add(new DashboardChildModel("Happiness", R.drawable.bg_h, false));
            celebritesArrayList.add(new DashboardChildModel("Relationship", R.drawable.bg_r, false));
            celebritesArrayList.add(new DashboardChildModel("Success", R.drawable.bg_s, false));
            celebritesArrayList.add(new DashboardChildModel("Love", R.drawable.bg_l, false));
            celebritesArrayList.add(new DashboardChildModel("Alone", R.drawable.bg_a, false));
            celebritesArrayList.add(new DashboardChildModel("Birthday", R.drawable.bg_c, false));
            celebritesArrayList.add(new DashboardChildModel("Business", R.drawable.bg_bb, false));
            celebritesArrayList.add(new DashboardChildModel("Dreams", R.drawable.bg_d, false));
            celebritesArrayList.add(new DashboardChildModel("Failure", R.drawable.bg_ff, false));
            celebritesArrayList.add(new DashboardChildModel("Fitness", R.drawable.bg_fff, false));
            celebritesArrayList.add(new DashboardChildModel("Forgiveness", R.drawable.bg_ffff, false));
            celebritesArrayList.add(new DashboardChildModel("Mom", R.drawable.bg_m, false));
            celebritesArrayList.add(new DashboardChildModel("Friendship", R.drawable.bg_f, false));
            celebritesArrayList.add(new DashboardChildModel("Imagination", R.drawable.bg_ii, false));
            celebritesArrayList.add(new DashboardChildModel("Life", R.drawable.bg_ll, false));
            celebritesArrayList.add(new DashboardChildModel("Sad", R.drawable.bg_s, false));
            celebritesArrayList.add(new DashboardChildModel("Knowledge", R.drawable.bg_k, false));
            celebritesArrayList.add(new DashboardChildModel("Marriage", R.drawable.bg_mmm, false));
            celebritesArrayList.add(new DashboardChildModel("Romantic", R.drawable.bg_r, false));
            celebritesArrayList.add(new DashboardChildModel("Coming Soon",R.drawable.coming_soon));
        } else if (dashboardParentModel.getParentItemTitle().equals(getResources().getString(R.string.title_top_biographies))) {
            celebritesArrayList.clear();
            celebritesArrayList.add(new DashboardChildModel("Nikola tesla", R.drawable.nikol));
            celebritesArrayList.add(new DashboardChildModel("Mother teresa", R.drawable.mothertera));
            celebritesArrayList.add(new DashboardChildModel("Einstein", R.drawable.img_albert));
            celebritesArrayList.add(new DashboardChildModel("Steve jobs", R.drawable.img_steve));
            celebritesArrayList.add(new DashboardChildModel("Swami vivekananda", R.drawable.img_vivekanada));
            celebritesArrayList.add(new DashboardChildModel("Bill gates", R.drawable.biily));
            celebritesArrayList.add(new DashboardChildModel("Sachin tendulkar", R.drawable.sachin));
            celebritesArrayList.add(new DashboardChildModel("Abdul kalam", R.drawable.img_kalam));
            celebritesArrayList.add(new DashboardChildModel("Narendra modi", R.drawable.img_modi));
            celebritesArrayList.add(new DashboardChildModel("Elon musk", R.drawable.img_elon));
            celebritesArrayList.add(new DashboardChildModel("Jack ma", R.drawable.imgjackma));
            celebritesArrayList.add(new DashboardChildModel("Vallabhbhai patel", R.drawable.sardar));
            celebritesArrayList.add(new DashboardChildModel("Mahatma gandhi", R.drawable.gandhi));
            celebritesArrayList.add(new DashboardChildModel("chanakya", R.drawable.img_chankya));
            celebritesArrayList.add(new DashboardChildModel("Coming Soon",R.drawable.coming_soon));
        } else if (dashboardParentModel.getParentItemTitle().equals(getResources().getString(R.string.title_top_lessons))) {
            celebritesArrayList.clear();
            celebritesArrayList.add(new DashboardChildModel("Life Goals", R.drawable.bg_ll_l, 1));
            celebritesArrayList.add(new DashboardChildModel("Career", R.drawable.bg_ll_c, 1));
            celebritesArrayList.add(new DashboardChildModel("Lifestyle", R.drawable.bg_ll_ll, 1));
            celebritesArrayList.add(new DashboardChildModel("Valuable", R.drawable.bg_ll_v, 1));
            celebritesArrayList.add(new DashboardChildModel("Coming Soon",R.drawable.coming_soon,1));

        }
        if (rewardedAd.isLoaded() || !rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                }

                @Override
                public void onRewardedAdFailedToShow(AdError adError) {
                    // Ad failed to display.
                }
            };
            if (!AppConstants.isSaved) {
                rewardedAd.show(getActivity(), adCallback);
            }

            Intent intent = new Intent(getActivity(), GridActivity.class);
            intent.putParcelableArrayListExtra(AppConstants.PARENT_ARRAYLIST, celebritesArrayList);
            intent.putExtra(AppConstants.PARENT_TITLE, dashboardParentModel.getParentItemTitle());
            startActivity(intent);
        /*else if (dashboardParentModel.isViewMore()){
            if (dashboardParentModel.getParentItemTitle().equals(mainTitle)){
                Intent intent = new Intent(mContext,GridActivity.class);
                intent.putParcelableArrayListExtra(AppConstants.PARENT_ARRAYLIST, userQuotesArrayList);
                startActivity(intent);
            }
        }*/
        }
    }


}