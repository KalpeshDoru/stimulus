package com.imkalpesh.stimulus.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.adapers.dashboard.DashboardChildAdapter;
import com.imkalpesh.stimulus.baseclasses.BaseActivity;
import com.imkalpesh.stimulus.databinding.ActivityGridBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.models.LessonModel;
import com.imkalpesh.stimulus.models.QuotesModel;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;

public class GridActivity extends BaseActivity implements CommonActionListener, View.OnClickListener {
    private ActivityGridBinding binding;
    private Context mContext = this;
    private LessonModel lessonModel;
    private ArrayList<QuotesModel> quotesListActivityArrayList;
    private ArrayList<LessonModel> lessonModelArrayList;
    private ArrayList<DashboardChildModel> childItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid);
        binding.actionBar.ivBack.setVisibility(View.VISIBLE);
        binding.actionBar.ivBack.setOnClickListener(this);
        lessonModel = new LessonModel();
        childItemList = this.getIntent().getParcelableArrayListExtra(AppConstants.PARENT_ARRAYLIST);
        binding.actionBar.tvTitle.setText(getIntent().getStringExtra(AppConstants.PARENT_TITLE));

        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        binding.rvGrid.setLayoutManager(staggeredGridLayoutManager);
        DashboardChildAdapter dashboardChildAdapter = new DashboardChildAdapter(this, childItemList, this);
        binding.rvGrid.setAdapter(dashboardChildAdapter);
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       
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
        Intent intent = new Intent(mContext, QuotesListActivity.class);
        Intent intent2 = new Intent(mContext, LessonListActivity.class);
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
            case "Sundar Pichai":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Wear your failures as a badge of honor."));
                quotesListActivityArrayList.add(new QuotesModel("A person who is happy is not because everything is right in his life, he is happy because his attitude towards everything in his life is right."));
                quotesListActivityArrayList.add(new QuotesModel("India has been, has long been, an exporter of talent to tech companies. But it is in India that’s now undergoing its own revolution."));
                quotesListActivityArrayList.add(new QuotesModel("Keep pushing your limits."));
                quotesListActivityArrayList.add(new QuotesModel("It is important to follow your dreams and heart. Do something that excites you."));
                quotesListActivityArrayList.add(new QuotesModel("As a leader, it is important to not just see your success but focus on the success of others."));
                quotesListActivityArrayList.add(new QuotesModel("Let yourself feel insecure from time, it will help you grow as an individual"));
                quotesListActivityArrayList.add(new QuotesModel("In life don’t react, always respond."));
                quotesListActivityArrayList.add(new QuotesModel("You might fail a few times, but that’s Ok. You end up doing something worthwhile which you learn a great deal from."));
                quotesListActivityArrayList.add(new QuotesModel("Focus on education is a big strength. I want to see young people focus on creativity and take more risks."));
                quotesListActivityArrayList.add(new QuotesModel("We try to work on things which billions of people will use every day."));
                quotesListActivityArrayList.add(new QuotesModel("Good companies do whatever it takes."));
                quotesListActivityArrayList.add(new QuotesModel("Most of how life plays out is up to you."));
                quotesListActivityArrayList.add(new QuotesModel("If you don't fail sometimes, you are not being ambitious enough."));
                quotesListActivityArrayList.add(new QuotesModel("Be impatient. It will create the progress the world needs."));
                break;
            case "Abdul Kalam":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("All Birds find shelter during rain. But Eagle avoids rain by flying above clouds."));
                quotesListActivityArrayList.add(new QuotesModel("Don’t take rest after your first victory because if you fail in second, more lips are waiting to say that your first victory was just luck."));
                quotesListActivityArrayList.add(new QuotesModel("Don’t take rest after your first victory because if you fail in second, more lips are waiting to say that your first victory was just luck."));
                quotesListActivityArrayList.add(new QuotesModel("All of us do not have equal talent. But, all of us have an equal opportunity to develop our talents."));
                quotesListActivityArrayList.add(new QuotesModel("You have to dream before your dreams can come true."));
                quotesListActivityArrayList.add(new QuotesModel("Failure will never overtake me if my definition to succeed is strong enough."));
                quotesListActivityArrayList.add(new QuotesModel("I'm not a handsome guy, but I can give my hand to someone who needs help. Beauty is in the heart, not in the face."));
                quotesListActivityArrayList.add(new QuotesModel("The best brains of the nations may be found on the last benches of the classrooms."));
                quotesListActivityArrayList.add(new QuotesModel("You cannot change your future, but, you can change your habits, and surely your habits will change your future."));
                quotesListActivityArrayList.add(new QuotesModel("Look at the sky. We are not alone. The whole universe is friendly to us and conspires only to those who dream and work."));
                quotesListActivityArrayList.add(new QuotesModel("I will work and sweat for a great vision, the vision of transforming India into a developed nation."));
                quotesListActivityArrayList.add(new QuotesModel("Don’t read success stories, you will only get message. Read failure stories, you will get some ideas to get success."));
                quotesListActivityArrayList.add(new QuotesModel("Your best teacher is your last mistake."));
                quotesListActivityArrayList.add(new QuotesModel("One best book is equal to hundred good friends, but one good friend is equal to a library."));
                quotesListActivityArrayList.add(new QuotesModel("No matter what is the environment around you, it is always possible to maintain brand of integrity."));
                quotesListActivityArrayList.add(new QuotesModel("Man needs difficulties in life because they are necessary to enjoy the success."));
                quotesListActivityArrayList.add(new QuotesModel("Unless India stands up to the world, no one will respect us. In this world, fear has no place. Only strength respects strength."));
                quotesListActivityArrayList.add(new QuotesModel("Climbing to the top demands strength, whether it is to the top of Mount Everest or to the top of your career.  "));
                quotesListActivityArrayList.add(new QuotesModel("Thinking is the capital, Enterprise is the way, Hard Work is the solution"));
                quotesListActivityArrayList.add(new QuotesModel("Be active! Take on responsibility! Work for the things you believe in. If you do not, you are surrendering your fate to others."));

            case "Jack Ma":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Without internet, there would have been no Jack Ma, and no Alibaba or Taobao."));
                quotesListActivityArrayList.add(new QuotesModel("I’m not a tech guy. I’m looking at the technology with the eyes of my customers, normal people’s eyes"));
                quotesListActivityArrayList.add(new QuotesModel("Never ever do business with the government. Be in love with them, never marry them."));
                quotesListActivityArrayList.add(new QuotesModel("My job is to help more people have jobs."));
                quotesListActivityArrayList.add(new QuotesModel("When we have money, we start making mistakes."));
                quotesListActivityArrayList.add(new QuotesModel("We should never finish a 20 year program in two years."));
                quotesListActivityArrayList.add(new QuotesModel("If you’ve never tried, how will you ever know if there’s any chance?"));
                quotesListActivityArrayList.add(new QuotesModel("I’m a normal guy."));
                quotesListActivityArrayList.add(new QuotesModel("If you don’t give up, you still have a chance. Giving up is the greatest failure."));
                quotesListActivityArrayList.add(new QuotesModel("Life is so short, so beautiful. Don’t be so serious about work. Enjoy the lives."));
                quotesListActivityArrayList.add(new QuotesModel("Opportunity lies in the place where the complaints are."));
                quotesListActivityArrayList.add(new QuotesModel("When you are small, you have to be very focused and rely on your brain, not your strength."));
                quotesListActivityArrayList.add(new QuotesModel("You never know that the things you’re doing are that meaningful to society."));
                quotesListActivityArrayList.add(new QuotesModel("I don’t care about revenues"));
                quotesListActivityArrayList.add(new QuotesModel("If we are a good team and know what we want to do, one of us can defeat ten of them."));
                quotesListActivityArrayList.add(new QuotesModel("A leader should have higher grit and tenacity, and be able to endure what the employees can’t."));
                quotesListActivityArrayList.add(new QuotesModel("I don’t want to be liked. I want to be respected."));
                quotesListActivityArrayList.add(new QuotesModel("You never know how much can you do in your life."));
                quotesListActivityArrayList.add(new QuotesModel("A peace talk is always difficult, always complicated."));
                quotesListActivityArrayList.add(new QuotesModel("You should learn from your competitor, but never copy. Copy and you die."));
                quotesListActivityArrayList.add(new QuotesModel("It doesn’t matter if I failed. At least I passed the concept on to others. Even if I don’t succeed, someone will succeed"));
                quotesListActivityArrayList.add(new QuotesModel("I call myself a blind man riding on a blind tiger."));
                quotesListActivityArrayList.add(new QuotesModel("You should learn from your competitor, but never copy. Copy and you die."));
                quotesListActivityArrayList.add(new QuotesModel("The very important thing you should have is patience."));
                quotesListActivityArrayList.add(new QuotesModel("You need the right people with you, not the best people.”"));
                quotesListActivityArrayList.add(new QuotesModel("You’ve got to make your team have value, innovation, and vision."));
                quotesListActivityArrayList.add(new QuotesModel("Never ever compete on prices, instead compete on services and innovation."));
                quotesListActivityArrayList.add(new QuotesModel("If the customer loves you, the government will have to love you."));
                quotesListActivityArrayList.add(new QuotesModel("We appreciate yesterday, but we’re looking for a better tomorrow."));
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

            case "Albert Einstein":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("A person who never made a mistake never tried anything new."));
                quotesListActivityArrayList.add(new QuotesModel("Learn from yesterday, live for today, hope for tomorrow. The important thing is not to stop questioning."));
                quotesListActivityArrayList.add(new QuotesModel("Strive not to be a success, but rather to be of value."));
                quotesListActivityArrayList.add(new QuotesModel("Life is like riding a bicycle. To keep your balance, you must keep moving."));
                quotesListActivityArrayList.add(new QuotesModel("It's not that I'm so smart, it's just that I stay with problems longer."));
                quotesListActivityArrayList.add(new QuotesModel("Anyone who has never made a mistake has never tried anything new."));
                quotesListActivityArrayList.add(new QuotesModel("The true sign of intelligence is not knowledge but imagination."));
                quotesListActivityArrayList.add(new QuotesModel("I have no special talent. I am only passionately curious."));
                quotesListActivityArrayList.add(new QuotesModel("We can't solve problems by using the same kind of thinking we used when we created them."));
                quotesListActivityArrayList.add(new QuotesModel("It is the supreme art of the teacher to awaken joy in creative expression and knowledge."));
                quotesListActivityArrayList.add(new QuotesModel("Education is what remains after one has forgotten what one has learned in school."));
                quotesListActivityArrayList.add(new QuotesModel("Weakness of attitude becomes weakness of character."));
                quotesListActivityArrayList.add(new QuotesModel("The most beautiful thing we can experience is the mysterious. It is the source of all true art and science."));
                quotesListActivityArrayList.add(new QuotesModel("The only source of knowledge is experience."));
                quotesListActivityArrayList.add(new QuotesModel("Peace cannot be kept by force; it can only be achieved by understanding."));
                quotesListActivityArrayList.add(new QuotesModel("The monotony and solitude of a quiet life stimulates the creative mind."));
                quotesListActivityArrayList.add(new QuotesModel("Few are those who see with their own eyes and feel with their own hearts."));
                quotesListActivityArrayList.add(new QuotesModel("Try not to become a man of success, but rather try to become a man of value."));
                quotesListActivityArrayList.add(new QuotesModel("Once we accept our limits, we go beyond them."));
                quotesListActivityArrayList.add(new QuotesModel("Reality is merely an illusion, albeit a very persistent one."));
                quotesListActivityArrayList.add(new QuotesModel("Imagination is everything. It is the preview of life's coming attractions."));
                quotesListActivityArrayList.add(new QuotesModel("Technological progress is like an axe in the hands of a pathological criminal."));
                quotesListActivityArrayList.add(new QuotesModel("The only reason for time is so that everything doesn't happen at once."));
                quotesListActivityArrayList.add(new QuotesModel("I live in that solitude which is painful in youth, but delicious in the years of maturity."));
                quotesListActivityArrayList.add(new QuotesModel("Common sense is the collection of prejudices acquired by age eighteen."));
                quotesListActivityArrayList.add(new QuotesModel("Joy in looking and comprehending is nature's most beautiful gift."));
                quotesListActivityArrayList.add(new QuotesModel("When the solution is simple, God is answering."));
                quotesListActivityArrayList.add(new QuotesModel("The environment is everything that isn't me."));
                quotesListActivityArrayList.add(new QuotesModel("Force always attracts men of low morality."));
                quotesListActivityArrayList.add(new QuotesModel("It is strange to be known so universally and yet to be so lonely."));
                quotesListActivityArrayList.add(new QuotesModel("When the solution is simple, God is answering."));
                quotesListActivityArrayList.add(new QuotesModel("Everyone should be respected as an individual, but no one idolized."));
                quotesListActivityArrayList.add(new QuotesModel("It is strange to be known so universally and yet to be so lonely."));
                quotesListActivityArrayList.add(new QuotesModel("Intellectuals solve problems, geniuses prevent them."));
                break;

            case "Obama":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("If you're walking down the right path and you're willing to keep walking, eventually you'll make progress."));
                quotesListActivityArrayList.add(new QuotesModel("The future rewards those who press on. I don't have time to feel sorry for myself. I don't have time to complain. I'm going to press on."));
                quotesListActivityArrayList.add(new QuotesModel("We need to keep making our streets safer and our criminal justice system fairer - our homeland more secure, our world more peaceful and sustainable for the next generation."));
                quotesListActivityArrayList.add(new QuotesModel("My fellow Americans, we are and always will be a nation of immigrants. We were strangers once, too."));
                quotesListActivityArrayList.add(new QuotesModel("Issues are never simple. One thing I'm proud of is that very rarely will you hear me simplify the issues."));
                quotesListActivityArrayList.add(new QuotesModel("If the people cannot trust their government to do the job for which it exists - to protect them and to promote their common welfare - all else is lost."));
                quotesListActivityArrayList.add(new QuotesModel("We have real enemies in the world. These enemies must be found. They must be pursued and they must be defeated."));
                quotesListActivityArrayList.add(new QuotesModel("You know, my faith is one that admits some doubt."));
                quotesListActivityArrayList.add(new QuotesModel("We need to internalize this idea of excellence. Not many folks spend a lot of time trying to be excellent."));
                quotesListActivityArrayList.add(new QuotesModel("I will continue to believe that Israel's security is paramount."));
                quotesListActivityArrayList.add(new QuotesModel("Americans... still believe in an America where anything's possible - they just don't think their leaders do."));
                quotesListActivityArrayList.add(new QuotesModel("My job is not to represent Washington to you, but to represent you to Washington."));
                quotesListActivityArrayList.add(new QuotesModel("The United States is not, and never will be, at war with Islam."));
                quotesListActivityArrayList.add(new QuotesModel("In the end, that's what this election is about. Do we participate in a politics of cynicism or a politics of hope?"));
                quotesListActivityArrayList.add(new QuotesModel("The United States is not, and never will be, at war with Islam."));
                quotesListActivityArrayList.add(new QuotesModel("We need to internalize this idea of excellence. Not many folks spend a lot of time trying to be excellent."));
                quotesListActivityArrayList.add(new QuotesModel("My job is not to represent Washington to you, but to represent you to Washington."));
                quotesListActivityArrayList.add(new QuotesModel("The United States is not, and never will be, at war with Islam."));
                break;

            case "Chanakya":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("No one can defeat a powerful mind."));
                quotesListActivityArrayList.add(new QuotesModel("Learn from the mistakes of others….. you can’t live long enough to make them all yourselves."));
                quotesListActivityArrayList.add(new QuotesModel("The biggest guru-mantra is: never share your secrets with anybody. It will destroy you."));
                quotesListActivityArrayList.add(new QuotesModel("Even if a snake is not poisonous, it should pretend to be venomous."));
                quotesListActivityArrayList.add(new QuotesModel("A person should not be too honest. Straight trees are cut first and honest people are screwed first."));
                quotesListActivityArrayList.add(new QuotesModel("He who is overly attached to his family members, experiences fear and sorrow, for the root of all grief is attachment. Thus one should discard attachment to be happy."));
                quotesListActivityArrayList.add(new QuotesModel("A man is great by deeds, not by birth."));
                quotesListActivityArrayList.add(new QuotesModel("Education is the best friend. An educated person is respected everywhere. Education beats the beauty and the youth."));
                quotesListActivityArrayList.add(new QuotesModel("Test a servant while in the discharge of his duty, a relative difficulty, a friend in adversity, and a wife is misfortune."));
                quotesListActivityArrayList.add(new QuotesModel("Once you start working on something don’t be afraid of failure and don’t abandon it . People who work sincerely are the happiest."));
                quotesListActivityArrayList.add(new QuotesModel("As soon as the fear approaches near, attack and destroy it."));
                quotesListActivityArrayList.add(new QuotesModel("God is not present in idols. Your feelings are your God. The soul is your temple."));
                quotesListActivityArrayList.add(new QuotesModel("Wealth, a friend, a wife, and a kingdom may be regained, but this body when lost may never be acquired again."));
                quotesListActivityArrayList.add(new QuotesModel("Never make friends with people who are above or below you in status . Such friendships will never give you any happiness."));
                quotesListActivityArrayList.add(new QuotesModel("Books are as useful to a stupid person as a mirror is useful to a blind person."));
                quotesListActivityArrayList.add(new QuotesModel("As a single withered tree, if set aflame, causes a whole forest to burn, so does a rascal son destroy a whole family."));
                quotesListActivityArrayList.add(new QuotesModel("There is poison in the fang of the serpent, in the mouth of the fly and in the sting of a scorpion; but the wicked man is saturated with it."));
                quotesListActivityArrayList.add(new QuotesModel("The fragrance of flowers spreads only in the directions of the wind. But, the goodness of a person spreads in all direction."));
                quotesListActivityArrayList.add(new QuotesModel("Drop the idea that attachment and love are one thing. They are enemies. It is attachment that destroys all love."));
                quotesListActivityArrayList.add(new QuotesModel("Never reveal what you have thought upon doing, but by wise council keep it secret being determined to carry it into execution."));
                quotesListActivityArrayList.add(new QuotesModel("Accumulated wealth is saved by spending just as incoming fresh water is saved by letting out stagnant water."));
                quotesListActivityArrayList.add(new QuotesModel("Never settle for anything less than what you deserve. It’s not pride, it’s self respect."));
                quotesListActivityArrayList.add(new QuotesModel("It is better to die than preserve life by incurring disgrace. The loss of life only pains a moment, but disgrace every day of one’s life."));
                quotesListActivityArrayList.add(new QuotesModel("There are three gems upon this earth, food, water, and pleasing words — fools consider pieces of rocks as gems."));
                quotesListActivityArrayList.add(new QuotesModel("There are only two ways of dealing with the evil persons or thorns. Crush them under your boot or stay far away from them."));
                quotesListActivityArrayList.add(new QuotesModel("There is no present and future of the lazy."));
                break;

            case "B. R. Ambedkar":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Cultivation of mind should be the ultimate aim of human existence."));
                quotesListActivityArrayList.add(new QuotesModel("We are Indians, firstly and lastly."));
                quotesListActivityArrayList.add(new QuotesModel("Life should be great rather than long."));
                quotesListActivityArrayList.add(new QuotesModel("I measure the progress of a community by the degree of progress which women have achieved."));
                quotesListActivityArrayList.add(new QuotesModel("Law and order are the medicine of the body politic and when the body politic gets sick, medicine must be administered."));
                quotesListActivityArrayList.add(new QuotesModel("A great man is different from an eminent one in that he is ready to be the servant of the society."));
                quotesListActivityArrayList.add(new QuotesModel("Men are mortal. So are ideas. An idea needs propagation as much as a plant needs watering. Otherwise both will wither and die."));
                quotesListActivityArrayList.add(new QuotesModel("So long as you do not achieve social liberty, whatever freedom is provided by the law is of no avail to you."));
                quotesListActivityArrayList.add(new QuotesModel("The sovereignty of scriptures of all religions must come to an end if we want to have a united integrated modern India."));
                quotesListActivityArrayList.add(new QuotesModel("In Hinduism, conscience, reason and independent thinking have no scope for development."));
                quotesListActivityArrayList.add(new QuotesModel("Religion and slavery are incompatible."));
                quotesListActivityArrayList.add(new QuotesModel("Political tyranny is nothing compared to the social tyranny and a reformer who defies society is a more courageous man than a politician who defies Government."));
                quotesListActivityArrayList.add(new QuotesModel("I solemnly assure you that I will not die as a Hindu."));
                quotesListActivityArrayList.add(new QuotesModel("If I find the constitution being misused, I shall be the first to burn it."));
                quotesListActivityArrayList.add(new QuotesModel("I like the religion that teaches liberty, equality and fraternity."));
                quotesListActivityArrayList.add(new QuotesModel("Equality may be a fiction but nonetheless one must accept it as a governing principle."));
                quotesListActivityArrayList.add(new QuotesModel("Though, I was born a Hindu, I solemnly assure you that I will not die as a Hindu"));
                quotesListActivityArrayList.add(new QuotesModel("Religion is for man and not man for religion"));
                quotesListActivityArrayList.add(new QuotesModel("Indifferentism is the worst kind of disease that can affect people."));
                quotesListActivityArrayList.add(new QuotesModel("A safe army is better than a safe border"));
                quotesListActivityArrayList.add(new QuotesModel("The basic idea underlying religion is to create an atmosphere for the spiritual development of the individual"));
                break;

            case "Swami Vivekananda":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Arise, awake, and stop not until the goal is achieved"));
                quotesListActivityArrayList.add(new QuotesModel("Talk to yourself once in a day, otherwise, you may miss meeting an excellent person in this world."));
                quotesListActivityArrayList.add(new QuotesModel("If I love myself despite my infinite faults, how can I hate anyone at the glimpse of a few faults."));
                quotesListActivityArrayList.add(new QuotesModel("All the powers in the universe are already ours. It is we who we have put our hands before our eyes and cry that it is dark."));
                quotesListActivityArrayList.add(new QuotesModel("Take risks in your life. If you win, you can lead, if you lose, you can guide."));
                quotesListActivityArrayList.add(new QuotesModel("Be a hero. Always say, I have no fear."));
                quotesListActivityArrayList.add(new QuotesModel("The world is the great gymnasium where we come to make ourselves strong."));
                quotesListActivityArrayList.add(new QuotesModel("Who is helping you don’t forget them. Who is loving you, don’t hate them. Who is believing you, don’t cheat them."));
                quotesListActivityArrayList.add(new QuotesModel("The reason for every misunderstanding is that we see the people as we are but not as they are."));
                quotesListActivityArrayList.add(new QuotesModel("Let men have light, let them be pure and spiritually strong and educated, then alone will misery cease in the world, not before."));
                quotesListActivityArrayList.add(new QuotesModel("Do not wait for anybody or anything. Do whatever you can, build your hope on none."));
                quotesListActivityArrayList.add(new QuotesModel("We are what our thoughts have made us; so take care of what you think. Words are secondary. Thoughts live; they travel far."));
                quotesListActivityArrayList.add(new QuotesModel("All the negative thoughts and ideas that are in the world have proceeded from this evil spirit of fear."));
                quotesListActivityArrayList.add(new QuotesModel("A man is not poor without a rupee but a man is really poor without a dream and ambition."));
                quotesListActivityArrayList.add(new QuotesModel("Have faith enough and you will be free in a minute."));
                quotesListActivityArrayList.add(new QuotesModel("Anything that makes you weak, physically, intellectually, and spiritually, reject as a poison."));
                quotesListActivityArrayList.add(new QuotesModel("Do one thing at a time, and while doing it put your whole soul into it to the exclusion of all else."));
                quotesListActivityArrayList.add(new QuotesModel("Purity, patience, and perseverance are the three essentials to success and above all, love."));
                quotesListActivityArrayList.add(new QuotesModel("Doing good to others is the one great universal religion."));
                quotesListActivityArrayList.add(new QuotesModel("What I want is muscles of iron and nerves of steel, inside which dwells a mind of the same material as that of which the thunderbolt is made."));
                quotesListActivityArrayList.add(new QuotesModel("See for the highest, aim at the highest, and you shall reach the highest."));
                quotesListActivityArrayList.add(new QuotesModel("Neither seek nor avoid, take what comes."));
                quotesListActivityArrayList.add(new QuotesModel("All power is within you; you can do anything."));
                quotesListActivityArrayList.add(new QuotesModel("Believe in yourself."));
                break;

            case "Satya Nadela":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("If you don’t have a real stake in the new, then just surviving on the old – even if it is about efficiency – I don’t think is a long-term game."));
                quotesListActivityArrayList.add(new QuotesModel("The view you adopt for yourself profoundly affects the way you lead your life."));
                quotesListActivityArrayList.add(new QuotesModel("Microsoft is the productivity and Platform Company for the mobile-first and cloud-first world."));
                quotesListActivityArrayList.add(new QuotesModel("I think playing cricket taught me more about working in teams and leadership that has stayed with me throughout my career."));
                quotesListActivityArrayList.add(new QuotesModel("The way I measure my life is – Am I better than I was last year?"));
                quotesListActivityArrayList.add(new QuotesModel("There is something only a CEO uniquely can do, which is set that tone, which can then capture the soul of the collective."));
                quotesListActivityArrayList.add(new QuotesModel("Passion, toil, and training can help you to soar."));
                quotesListActivityArrayList.add(new QuotesModel("We must ensure not only that everyone receives equal pay for equal work, but that they have the opportunity to do equal work."));
                quotesListActivityArrayList.add(new QuotesModel("Longevity in this business is about being able to reinvent yourself or invent the future."));
                quotesListActivityArrayList.add(new QuotesModel("Learning to fly is not pretty but flying is"));
                quotesListActivityArrayList.add(new QuotesModel("If you are going to have a risk-taking culture, you can’t really look at every failure as a failure, you’ve got to be able to look at the failure as a learning opportunity."));
                quotesListActivityArrayList.add(new QuotesModel("When I think about my career, my successes are built on learning from failures."));
                quotesListActivityArrayList.add(new QuotesModel("Be passionate and bold."));
                quotesListActivityArrayList.add(new QuotesModel("We needed to convert Nietzsche’s courage in the face of reality, into courage in the face of opportunity."));
                quotesListActivityArrayList.add(new QuotesModel("I believe men and women should get equal pay for equal work."));
                quotesListActivityArrayList.add(new QuotesModel("This is a software-powered world."));
                quotesListActivityArrayList.add(new QuotesModel("I don’t want to fight old battles. I want to find new ones."));
                quotesListActivityArrayList.add(new QuotesModel("We want to empower people."));
                quotesListActivityArrayList.add(new QuotesModel("LinkedIn was an amazing deal for us to do because of their mission."));
                break;

            case "Steve Jobs":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("My favorite things in life don't cost any money. It's really clear that the most precious resource we all have is time."));
                quotesListActivityArrayList.add(new QuotesModel("Innovation distinguishes between a leader and a follower."));
                quotesListActivityArrayList.add(new QuotesModel("Be a yardstick of quality. Some people aren't used to an environment where excellence is expected."));
                quotesListActivityArrayList.add(new QuotesModel("Sometimes when you innovate, you make mistakes. It is best to admit them quickly, and get on with improving your other innovations."));
                quotesListActivityArrayList.add(new QuotesModel("You can't just ask customers what they want and then try to give that to them. By the time you get it built, they'll want something new."));
                quotesListActivityArrayList.add(new QuotesModel("It's not a faith in technology. It's faith in people."));
                quotesListActivityArrayList.add(new QuotesModel("Computers themselves, and software yet to be developed, will revolutionize the way we learn."));
                quotesListActivityArrayList.add(new QuotesModel("We made the buttons on the screen look so good you'll want to lick them."));
                quotesListActivityArrayList.add(new QuotesModel("Being the richest man in the cemetery doesn't matter to me. Going to bed at night saying we've done something wonderful, that's what matters to me."));
                quotesListActivityArrayList.add(new QuotesModel("Things don't have to change the world to be important."));
                quotesListActivityArrayList.add(new QuotesModel("Design is a funny word. Some people think design means how it looks. But of course, if you dig deeper, it's really how it works."));
                quotesListActivityArrayList.add(new QuotesModel("If you're gonna make connections which are innovative... you have to not have the same bag of experiences as everyone else does."));
                quotesListActivityArrayList.add(new QuotesModel("I've done a lot of things I'm not proud of, such as getting my girlfriend pregnant when I was 23 and the way I handled that."));
                quotesListActivityArrayList.add(new QuotesModel("For you to sleep well at night, the aesthetic, the quality, has to be carried all the way through."));
                quotesListActivityArrayList.add(new QuotesModel("I think we're having fun. I think our customers really like our products. And we're always trying to do better."));
                quotesListActivityArrayList.add(new QuotesModel("Who wants a stylus. You have to get em and put em away, and you lose em. Yuck. Nobody wants a stylus."));
                quotesListActivityArrayList.add(new QuotesModel("I'm very excited about having the Internet in my den."));
                quotesListActivityArrayList.add(new QuotesModel("I think right now it's a battle for the mindshare of developers and for the mindshare of customers, and right now iPhone and Android are winning that battle."));
                quotesListActivityArrayList.add(new QuotesModel("But Apple really beats to a different drummer. I used to say that Apple should be the Sony of this business, but in reality, I think Apple should be the Apple of this business."));
                quotesListActivityArrayList.add(new QuotesModel("You'll see more and more perfection of that - computer as servant. But the next thing is going to be computer as a guide or agent."));
                quotesListActivityArrayList.add(new QuotesModel("We hire people who want to make the best things in the world."));
                quotesListActivityArrayList.add(new QuotesModel("And one more thing."));
                quotesListActivityArrayList.add(new QuotesModel("I think money is a wonderful thing because it enables you to do things. It enables you to invest in ideas that don't have a short-term payback."));
                quotesListActivityArrayList.add(new QuotesModel("The seven-inch tablets are tweeners: too big to compete with a smartphone, and too small to compete with an iPad."));
                quotesListActivityArrayList.add(new QuotesModel("I get asked a lot why Apple's customers are so loyal. It's not because they belong to the Church of Mac! That's ridiculous."));
                quotesListActivityArrayList.add(new QuotesModel("Apple took the edge off the word 'computer."));
                break;

            case "Elon Musk":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("When something is important enough, you do it even if the odds are not in your favor."));
                quotesListActivityArrayList.add(new QuotesModel("Some people don't like change, but you need to embrace change if the alternative is disaster."));
                quotesListActivityArrayList.add(new QuotesModel("Failure is an option here. If things are not failing, you are not innovating enough."));
                quotesListActivityArrayList.add(new QuotesModel("Persistence is very important. You should not give up unless you are forced to give up."));
                quotesListActivityArrayList.add(new QuotesModel("I think it's very important to have a feedback loop, where you're constantly thinking about what you've done and how you could be doing it better."));
                quotesListActivityArrayList.add(new QuotesModel("There's a tremendous bias against taking risks. Everyone is trying to optimize their ass-covering."));
                quotesListActivityArrayList.add(new QuotesModel("It's OK to have your eggs in one basket as long as you control what happens to that basket."));
                quotesListActivityArrayList.add(new QuotesModel("I don't think it's a good idea to plan to sell a company."));
                quotesListActivityArrayList.add(new QuotesModel("People work better when they know what the goal is and why. It is important that people look forward to coming to work in the morning and enjoy working."));
                quotesListActivityArrayList.add(new QuotesModel("I say something, and then it usually happens. Maybe not on schedule, but it usually happens."));
                quotesListActivityArrayList.add(new QuotesModel("I don't spend my time pontificating about high-concept things; I spend my time solving engineering and manufacturing problems."));
                quotesListActivityArrayList.add(new QuotesModel("I don't create companies for the sake of creating companies, but to get things done."));
                quotesListActivityArrayList.add(new QuotesModel("Starting and growing a business is as much about the innovation, drive, and determination of the people behind it as the product they sell."));
                quotesListActivityArrayList.add(new QuotesModel("The first step is to establish that something is possible; then probability will occur."));
                quotesListActivityArrayList.add(new QuotesModel("I've actually not read any books on time management."));
                quotesListActivityArrayList.add(new QuotesModel("Really pay attention to negative feedback and solicit it, particularly from friends. ... Hardly anyone does that, and it's incredibly helpful."));
                quotesListActivityArrayList.add(new QuotesModel("If you get up in the morning and think the future is going to be better, it is a bright day. Otherwise, it's not."));
                quotesListActivityArrayList.add(new QuotesModel("What makes innovative thinking happen?... I think it's really a mindset. You have to decide."));
                quotesListActivityArrayList.add(new QuotesModel("People should pursue what they're passionate about. That will make them happier than pretty much anything else."));
                quotesListActivityArrayList.add(new QuotesModel("Being an entrepreneur is like eating glass and staring into the abyss of death."));
                quotesListActivityArrayList.add(new QuotesModel("I think we have a duty to maintain the light of consciousness to make sure it continues into the future."));
                quotesListActivityArrayList.add(new QuotesModel("You shouldn't do things differently just because they're different. They need to be... better."));
                quotesListActivityArrayList.add(new QuotesModel("You have to say, 'Well, why did it succeed where others did not."));
                quotesListActivityArrayList.add(new QuotesModel("We have a strict 'no-assholes policy' at SpaceX."));
                quotesListActivityArrayList.add(new QuotesModel("Disruptive technology where you really have a big technology discontinuity... tends to come from new companies."));
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

            case "Love":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Let us always meet each other with smile, for the smile is the beginning of love."));
                quotesListActivityArrayList.add(new QuotesModel("To love oneself is the beginning of a lifelong romance."));
                quotesListActivityArrayList.add(new QuotesModel("Love isn't something you find. Love is something that finds you."));
                quotesListActivityArrayList.add(new QuotesModel("Life is the flower for which love is the honey."));
                quotesListActivityArrayList.add(new QuotesModel("Love has no age, no limit; and no death."));
                quotesListActivityArrayList.add(new QuotesModel("Love cures people - both the ones who give it and the ones who receive it."));
                quotesListActivityArrayList.add(new QuotesModel("Love's greatest gift is its ability to make everything it touches sacred."));
                quotesListActivityArrayList.add(new QuotesModel("True love is like ghosts, which everyone talks about and few have seen."));
                quotesListActivityArrayList.add(new QuotesModel("A very small degree of hope is sufficient to cause the birth of love."));
                quotesListActivityArrayList.add(new QuotesModel("Great thoughts come from the heart."));
                quotesListActivityArrayList.add(new QuotesModel("Love is like an hourglass, with the heart filling up as the brain empties."));
                quotesListActivityArrayList.add(new QuotesModel("Love can sometimes be magic. But magic can sometimes... just be an illusion."));
                quotesListActivityArrayList.add(new QuotesModel("Love doesn't make the world go 'round. Love is what makes the ride worthwhile."));
                quotesListActivityArrayList.add(new QuotesModel("Love is an act of endless forgiveness, a tender look which becomes a habit.."));
                quotesListActivityArrayList.add(new QuotesModel("True love cannot be found where it does not exist, nor can it be denied where it does.."));
                quotesListActivityArrayList.add(new QuotesModel("Spread love everywhere you go. Let no one ever come to you without leaving happier."));
                quotesListActivityArrayList.add(new QuotesModel("A new command I give you: Love one another. As I have loved you, so you must love one another."));
                quotesListActivityArrayList.add(new QuotesModel("I have decided to stick with love. Hate is too great a burden to bear."));
                quotesListActivityArrayList.add(new QuotesModel("Love is a game that two can play and both win."));
                quotesListActivityArrayList.add(new QuotesModel("Only do what your heart tells you."));
                quotesListActivityArrayList.add(new QuotesModel("Nobody has ever measured, not even poets, how much the heart can hold."));
                quotesListActivityArrayList.add(new QuotesModel("Love is the hardest habit to break, and the most difficult to satisfy."));
                quotesListActivityArrayList.add(new QuotesModel("With our love, we could save the world."));
                quotesListActivityArrayList.add(new QuotesModel("The course of true love never did run smooth."));
                quotesListActivityArrayList.add(new QuotesModel("Love takes up where knowledge leaves off."));
                quotesListActivityArrayList.add(new QuotesModel("Love takes up where knowledge leaves off."));
                quotesListActivityArrayList.add(new QuotesModel("At the touch of love everyone becomes a poet."));
                break;

            case "Alone":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("It is far better to be alone, than to be in bad company."));
                quotesListActivityArrayList.add(new QuotesModel("If you are lonely when you're alone, you are in bad company."));
                quotesListActivityArrayList.add(new QuotesModel("The strongest man in the world is he who stands most alone."));
                quotesListActivityArrayList.add(new QuotesModel("If you make friends with yourself you will never be alone."));
                quotesListActivityArrayList.add(new QuotesModel("You cannot be lonely if you like the person you're alone with."));
                quotesListActivityArrayList.add(new QuotesModel("I never said, 'I want to be alone.' I only said, 'I want to be left alone.' There is all the difference."));
                quotesListActivityArrayList.add(new QuotesModel("Every man is born as many men and dies as a single one."));
                quotesListActivityArrayList.add(new QuotesModel("The time you feel lonely is the time you most need to be by yourself."));
                quotesListActivityArrayList.add(new QuotesModel("I restore myself when I'm alone."));
                quotesListActivityArrayList.add(new QuotesModel("Alone we can do so little; together we can do so much."));
                quotesListActivityArrayList.add(new QuotesModel("It's better to be unhappy alone than unhappy with someone - so far."));
                quotesListActivityArrayList.add(new QuotesModel("It is better to be alone than in bad company."));
                quotesListActivityArrayList.add(new QuotesModel("All men's misfortunes spring from their hatred of being alone."));
                quotesListActivityArrayList.add(new QuotesModel("I was never less alone than when by myself."));
                quotesListActivityArrayList.add(new QuotesModel("The only real progress lies in learning to be wrong all alone."));
                quotesListActivityArrayList.add(new QuotesModel("To live alone is the fate of all great souls."));
                quotesListActivityArrayList.add(new QuotesModel("Life could be wonderful if people would leave you alone."));
                quotesListActivityArrayList.add(new QuotesModel("Life could be wonderful if people would leave you alone."));
                quotesListActivityArrayList.add(new QuotesModel("It is strange to be known so universally and yet to be so lonely."));
                quotesListActivityArrayList.add(new QuotesModel("The longer one is alone, the easier it is to hear the song of the earth."));
                quotesListActivityArrayList.add(new QuotesModel("The lonely become either thoughtful or empty."));
                quotesListActivityArrayList.add(new QuotesModel("Unless we love and are loved, each of us is alone, each of us is deeply lonely."));
                quotesListActivityArrayList.add(new QuotesModel("You can't fake it when you're alone with God, you know."));
                quotesListActivityArrayList.add(new QuotesModel("As a body everyone is single, as a soul never"));
                quotesListActivityArrayList.add(new QuotesModel("I want to be alone."));
                break;
            case "Birthday":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Let us never know what old age is. Let us know the happiness time brings, not count the years."));
                quotesListActivityArrayList.add(new QuotesModel("How old would you be if you didn't know how old you are?"));
                quotesListActivityArrayList.add(new QuotesModel("God gave us the gift of life; it is up to us to give ourselves the gift of living well."));
                quotesListActivityArrayList.add(new QuotesModel("All the world is birthday cake, so take a piece, but not too much."));
                quotesListActivityArrayList.add(new QuotesModel("You don't get older, you get better."));
                quotesListActivityArrayList.add(new QuotesModel("The more you praise and celebrate your life, the more there is in life to celebrate."));
                quotesListActivityArrayList.add(new QuotesModel("I intend to live forever. So far, so good."));
                quotesListActivityArrayList.add(new QuotesModel("Our birthdays are feathers in the broad wing of time."));
                quotesListActivityArrayList.add(new QuotesModel("The way I see it, you should live everyday like its your birthday."));
                quotesListActivityArrayList.add(new QuotesModel("Age is a case of mind over matter. If you don't mind, it don't matter."));
                quotesListActivityArrayList.add(new QuotesModel("I wanted to buy a candle holder, but the store didn't have one. So I got a cake."));
                quotesListActivityArrayList.add(new QuotesModel("If you can give your child only one gift, let it be enthusiasm."));
                quotesListActivityArrayList.add(new QuotesModel("Like many women my age, I am 28 years old."));
                quotesListActivityArrayList.add(new QuotesModel("People give one another things that can't be gift wrapped."));
                quotesListActivityArrayList.add(new QuotesModel("Youth has no age."));
                quotesListActivityArrayList.add(new QuotesModel("A gift, with a kind countenance, is a double present."));
                quotesListActivityArrayList.add(new QuotesModel("At my age flowers scare me."));
                quotesListActivityArrayList.add(new QuotesModel("I'm not going to be caught around here for any fool celebration. To hell with birthdays!"));
                quotesListActivityArrayList.add(new QuotesModel("A friend never defends a husband who gets his wife an electric skillet for her birthday."));
                break;

            case "Business":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Sooner or later, those who win are those who think they can."));
                quotesListActivityArrayList.add(new QuotesModel("If you owe the bank $100 that's your problem. If you owe the bank $100 million, that's the bank's problem."));
                quotesListActivityArrayList.add(new QuotesModel("It is not from the benevolence of the butcher, the brewer, or the baker that we expect our dinner, but from their regard to their own interest."));
                quotesListActivityArrayList.add(new QuotesModel("The secret of business is to know something that nobody else knows."));
                quotesListActivityArrayList.add(new QuotesModel("A brand for a company is like a reputation for a person. You earn reputation by trying to do hard things well."));
                quotesListActivityArrayList.add(new QuotesModel("Corporation: An ingenious device for obtaining profit without individual responsibility."));
                quotesListActivityArrayList.add(new QuotesModel("Our work is the presentation of our capabilities."));
                quotesListActivityArrayList.add(new QuotesModel("The entrepreneur always searches for change, responds to it, and exploits it as an opportunity."));
                quotesListActivityArrayList.add(new QuotesModel("It's OK to have your eggs in one basket as long as you control what happens to that basket."));
                quotesListActivityArrayList.add(new QuotesModel("One of the tests of leadership is the ability to recognize a problem before it becomes an emergency."));
                quotesListActivityArrayList.add(new QuotesModel("If you don't drive your business, you will be driven out of business."));
                quotesListActivityArrayList.add(new QuotesModel("Failure doesn't mean you are a failure it just means you haven't succeeded yet."));
                quotesListActivityArrayList.add(new QuotesModel("Just because something doesn't do what you planned it to do doesn't mean it's useless."));
                quotesListActivityArrayList.add(new QuotesModel("Ideas pull the trigger, but instinct loads the gun."));
                quotesListActivityArrayList.add(new QuotesModel("Ask five economists and you'll get five different answers - six if one went to Harvard."));
                quotesListActivityArrayList.add(new QuotesModel("We're all working together; that's the secret."));
                quotesListActivityArrayList.add(new QuotesModel("Details create the big picture."));
                quotesListActivityArrayList.add(new QuotesModel("Carpe per diem - seize the check."));
                quotesListActivityArrayList.add(new QuotesModel("Failure doesn't mean you are a failure it just means you haven't succeeded yet."));
                quotesListActivityArrayList.add(new QuotesModel("Just because something doesn't do what you planned it to do doesn't mean it's useless."));
                quotesListActivityArrayList.add(new QuotesModel("Ask five economists and you'll get five different answers - six if one went to Harvard."));
                quotesListActivityArrayList.add(new QuotesModel("We're all working together; that's the secret."));
                quotesListActivityArrayList.add(new QuotesModel("I want to put a ding in the universe."));
                break;

            case "Dream":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Dream as if you'll live forever. Live as if you'll die today."));
                quotesListActivityArrayList.add(new QuotesModel("To accomplish great things, we must not only act, but also dream; not only plan, but also believe."));
                quotesListActivityArrayList.add(new QuotesModel("I'm a dreamer. I have to dream and reach for the stars, and if I miss a star then I grab a handful of clouds."));
                quotesListActivityArrayList.add(new QuotesModel("Living in dreams of yesterday, we find ourselves still dreaming of impossible future conquests."));
                quotesListActivityArrayList.add(new QuotesModel("A dream doesn't become reality through magic; it takes sweat, determination and hard work."));
                quotesListActivityArrayList.add(new QuotesModel("Who looks outside, dreams; who looks inside, awakes."));
                quotesListActivityArrayList.add(new QuotesModel("You have to dream before your dreams can come true."));
                quotesListActivityArrayList.add(new QuotesModel("Ideologies separate us. Dreams and anguish bring us together."));
                quotesListActivityArrayList.add(new QuotesModel("The future belongs to those who believe in the beauty of their dreams"));
                quotesListActivityArrayList.add(new QuotesModel("I don't use drugs, my dreams are frightening enough."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing happens unless first we dream."));
                quotesListActivityArrayList.add(new QuotesModel("I dream of painting and then I paint my dream."));
                quotesListActivityArrayList.add(new QuotesModel("Hold fast to dreams For when dreams go Life is a barren field Frozen with snow."));
                quotesListActivityArrayList.add(new QuotesModel("It takes a lot of courage to show your dreams to someone else."));
                quotesListActivityArrayList.add(new QuotesModel("I know how men in exile feed on dreams."));
                quotesListActivityArrayList.add(new QuotesModel("When we can't dream any longer we die."));
                quotesListActivityArrayList.add(new QuotesModel("When you cease to dream you cease to live."));
                quotesListActivityArrayList.add(new QuotesModel("I am a dreamer. Seriously, I'm living on another planet."));
                quotesListActivityArrayList.add(new QuotesModel("To all, to each, a fair good-night, and pleasing dreams, and slumbers light."));
                quotesListActivityArrayList.add(new QuotesModel("The more you can dream, the more you can do."));
                quotesListActivityArrayList.add(new QuotesModel("Youth is a wonderful thing. What a crime to waste it on children."));
                quotesListActivityArrayList.add(new QuotesModel("You have to have a dream so you can get up in the morning."));
                quotesListActivityArrayList.add(new QuotesModel("Judge of your natural character by what you do in your dreams."));
                quotesListActivityArrayList.add(new QuotesModel("Dream in a pragmatic way."));
                quotesListActivityArrayList.add(new QuotesModel("Saddle your dreams before you ride em."));
                quotesListActivityArrayList.add(new QuotesModel("Dreams are the touchstones of our character."));
                quotesListActivityArrayList.add(new QuotesModel("Where all is but dream, reasoning and arguments are of no use, truth and knowledge nothing."));
                quotesListActivityArrayList.add(new QuotesModel("He was a dreamer, a thinker, a speculative philosopher... or, as his wife would have it, an idiot."));
                quotesListActivityArrayList.add(new QuotesModel("Dream as if you'll live forever. Live as if you'll die today."));
                break;

            case "Failure":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("I have not failed. I've just found 10,000 ways that won't work."));
                quotesListActivityArrayList.add(new QuotesModel("You always pass failure on your way to success."));
                quotesListActivityArrayList.add(new QuotesModel("Failure is the condiment that gives success its flavor."));
                quotesListActivityArrayList.add(new QuotesModel("Failure is the key to success; each mistake teaches us something."));
                quotesListActivityArrayList.add(new QuotesModel("Sometimes by losing a battle you find a new way to win the war."));
                quotesListActivityArrayList.add(new QuotesModel("Every adversity, every failure, every heartache carries with it the seed of an equal or greater benefit."));
                quotesListActivityArrayList.add(new QuotesModel("Every failure is a step to success."));
                quotesListActivityArrayList.add(new QuotesModel("An essential aspect of creativity is not being afraid to fail."));
                quotesListActivityArrayList.add(new QuotesModel("Do not be embarrassed by your failures, learn from them and start again."));
                quotesListActivityArrayList.add(new QuotesModel("Socialism is a philosophy of failure, the creed of ignorance, and the gospel of envy, its inherent virtue is the equal sharing of misery."));
                quotesListActivityArrayList.add(new QuotesModel("My great concern is not whether you have failed, but whether you are content with your failure."));
                quotesListActivityArrayList.add(new QuotesModel("Success comes when people act together; failure tends to happen alone."));
                quotesListActivityArrayList.add(new QuotesModel("Mistakes are the portals of discovery."));
                quotesListActivityArrayList.add(new QuotesModel("Failure is a word unknown to me."));
                quotesListActivityArrayList.add(new QuotesModel("Mistakes are the portals of discovery."));
                quotesListActivityArrayList.add(new QuotesModel("Through perseverance many people win success out of what seemed destined to be certain failure"));
                quotesListActivityArrayList.add(new QuotesModel("Success isn't permanent and failure isn't fatal."));
                quotesListActivityArrayList.add(new QuotesModel("There are some defeats more triumphant than victories."));
                quotesListActivityArrayList.add(new QuotesModel("If at first you don't succeed, failure may be your style."));
                quotesListActivityArrayList.add(new QuotesModel("You make mistakes. Mistakes don't make you."));
                quotesListActivityArrayList.add(new QuotesModel("There are some defeats more triumphant than victories."));
                quotesListActivityArrayList.add(new QuotesModel("Failure is always an option."));
                quotesListActivityArrayList.add(new QuotesModel("If at first you don't succeed, failure may be your style."));
                quotesListActivityArrayList.add(new QuotesModel("The surest way to fail is not to determine to succeed."));
                break;

            case "Fitness":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Take care of your body. It's the only place you have to live."));
                quotesListActivityArrayList.add(new QuotesModel("To enjoy the glow of good health, you must exercise."));
                quotesListActivityArrayList.add(new QuotesModel("Reading is to the mind what exercise is to the body."));
                quotesListActivityArrayList.add(new QuotesModel("True enjoyment comes from activity of the mind and exercise of the body; the two are ever united."));
                quotesListActivityArrayList.add(new QuotesModel("The human body is the best picture of the human soul."));
                quotesListActivityArrayList.add(new QuotesModel("Find fitness with fun dancing. It is fun and makes you forget about the dreaded exercise."));
                quotesListActivityArrayList.add(new QuotesModel("Time and health are two precious assets that we don't recognize and appreciate until they have been depleted."));
                quotesListActivityArrayList.add(new QuotesModel("Exercise should be regarded as tribute to the heart."));
                quotesListActivityArrayList.add(new QuotesModel("Health is the thing that makes you feel that now is the best time of the year."));
                quotesListActivityArrayList.add(new QuotesModel("You must also give mental and physical fitness priority."));
                quotesListActivityArrayList.add(new QuotesModel("I didn't have the same fitness or ability as the other girls, so I had to beat them with my mind."));
                quotesListActivityArrayList.add(new QuotesModel("And I believe that the best buy in public health today must be a combination of regular physical exercise and a healthy diet."));
                quotesListActivityArrayList.add(new QuotesModel("Exercise is the chief source of improvement in our faculties."));
                quotesListActivityArrayList.add(new QuotesModel("You can't be fat and fast, too; so lift, run, diet and work."));
                quotesListActivityArrayList.add(new QuotesModel("Exercise is amazing, from the inside out. I feel so alive and have more energy."));
                quotesListActivityArrayList.add(new QuotesModel("Tactics, fitness, stroke ability, adaptability, experience, and sportsmanship are all necessary for winning."));
                quotesListActivityArrayList.add(new QuotesModel("Our growing softness, our increasing lack of physical fitness, is a menace to our security."));
                quotesListActivityArrayList.add(new QuotesModel("When I'm on the road, I'll break my exercise into a cardio session and a weights session."));
                quotesListActivityArrayList.add(new QuotesModel("The only way for a rich man to be healthy is by exercise and abstinence, to live as if he were poor."));
                quotesListActivityArrayList.add(new QuotesModel("Our growing softness, our increasing lack of physical fitness, is a menace to our security."));
                quotesListActivityArrayList.add(new QuotesModel("Health is the vital principle of bliss, and exercise, of health."));
                quotesListActivityArrayList.add(new QuotesModel("I do yoga, I do Bikram and I run, and I eat really healthy."));
                quotesListActivityArrayList.add(new QuotesModel("It's challenging, but you have to at least try to eat right and exercise."));
                quotesListActivityArrayList.add(new QuotesModel("II would rather exercise than read a newspaper."));
                quotesListActivityArrayList.add(new QuotesModel("You gotta have a body."));
                break;

            case "Forgiveness":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("To forgive is to set a prisoner free and discover that the prisoner was you."));
                quotesListActivityArrayList.add(new QuotesModel("Darkness cannot drive out darkness; only light can do that. Hate cannot drive out hate; only love can do that."));
                quotesListActivityArrayList.add(new QuotesModel("Sins cannot be undone, only forgiven.."));
                quotesListActivityArrayList.add(new QuotesModel("We think that forgiveness is weakness, but it's absolutely not; it takes a very strong person to forgive."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is a virtue of the brave."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing inspires forgiveness quite like revenge."));
                quotesListActivityArrayList.add(new QuotesModel("We win by tenderness. We conquer by forgiveness."));
                quotesListActivityArrayList.add(new QuotesModel("It's not an easy journey, to get to a place where you forgive people. But it is such a powerful place, because it frees you."));
                quotesListActivityArrayList.add(new QuotesModel("It is easier to forgive an enemy than to forgive a friend."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is the fragrance that the violet sheds on the heel that has crushed it."));
                quotesListActivityArrayList.add(new QuotesModel("The practice of forgiveness is our most important contribution to the healing of the world."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is the key to action and freedom."));
                quotesListActivityArrayList.add(new QuotesModel("God forgive you, but I never can."));
                quotesListActivityArrayList.add(new QuotesModel("If there is something to pardon in everything, there is also something to condemn."));
                quotesListActivityArrayList.add(new QuotesModel("Thank you, God, for this good life and forgive us if we do not love it enough."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is the needle that knows how to mend."));
                quotesListActivityArrayList.add(new QuotesModel("One forgives to the degree that one loves."));
                quotesListActivityArrayList.add(new QuotesModel("Knowing that you are completely forgiven destroys the power of sin in your life."));
                quotesListActivityArrayList.add(new QuotesModel("We often forgive those who bore us, but we cannot forgive those whom we bore."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is the giving, and so the receiving, of life."));
                quotesListActivityArrayList.add(new QuotesModel("I can have peace of mind only when I forgive rather than judge."));
                quotesListActivityArrayList.add(new QuotesModel("As we know, forgiveness of oneself is the hardest of all the forgivenesses."));
                quotesListActivityArrayList.add(new QuotesModel("Forgiveness is the giving, and so the receiving, of life."));
                break;

            case "Friendship":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("The greatest gift of life is friendship, and I have received it."));
                quotesListActivityArrayList.add(new QuotesModel("There are no strangers here; Only friends you haven't yet met."));
                quotesListActivityArrayList.add(new QuotesModel("It's the friends you can call up at 4 a.m. that matter."));
                quotesListActivityArrayList.add(new QuotesModel("Friends are the siblings God never gave us."));
                quotesListActivityArrayList.add(new QuotesModel("Things are never quite as scary when you've got a best friend."));
                quotesListActivityArrayList.add(new QuotesModel("No person is your friend who demands your silence, or denies your right to grow."));
                quotesListActivityArrayList.add(new QuotesModel("The best time to make friends is before you need them."));
                quotesListActivityArrayList.add(new QuotesModel("True friendship is like sound health; the value of it is seldom known until it is lost."));
                quotesListActivityArrayList.add(new QuotesModel("The only way to have a friend is to be one."));
                quotesListActivityArrayList.add(new QuotesModel("A man's friendships are one of the best measures of his worth."));
                quotesListActivityArrayList.add(new QuotesModel("The world is round so that friendship may encircle it."));
                quotesListActivityArrayList.add(new QuotesModel("I don't need a friend who changes when I change and who nods when I nod; my shadow does that much better."));
                quotesListActivityArrayList.add(new QuotesModel("Friends are born, not made."));
                quotesListActivityArrayList.add(new QuotesModel("The language of friendship is not words but meanings."));
                quotesListActivityArrayList.add(new QuotesModel("Friendship multiplies the good of life and divides the evil."));
                quotesListActivityArrayList.add(new QuotesModel("If you have one true friend you have more than your share."));
                quotesListActivityArrayList.add(new QuotesModel("Never explain - your friends do not need it and your enemies will not believe you anyway."));
                quotesListActivityArrayList.add(new QuotesModel("Lay this unto your breast: Old friends, like old swords, still are trusted best."));
                quotesListActivityArrayList.add(new QuotesModel("True friends stab you in the front."));
                quotesListActivityArrayList.add(new QuotesModel("It is not so much our friends' help that helps us, as the confidence of their help."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing but heaven itself is better than a friend who is really a friend."));
                quotesListActivityArrayList.add(new QuotesModel("Laughter is not at all a bad beginning for a friendship, and it is far the best ending for one."));
                quotesListActivityArrayList.add(new QuotesModel("A friend may well be reckoned the masterpiece of nature."));
                quotesListActivityArrayList.add(new QuotesModel("A friend in power is a friend lost."));
                quotesListActivityArrayList.add(new QuotesModel("Never do a wrong thing to make a friend or to keep one."));
                quotesListActivityArrayList.add(new QuotesModel("Fear makes strangers of people who would be friends."));
                quotesListActivityArrayList.add(new QuotesModel("Some people go to priests; others to poetry; I to my friends."));
                quotesListActivityArrayList.add(new QuotesModel("Friends... they cherish one another's hopes. They are kind to one another's dreams."));
                quotesListActivityArrayList.add(new QuotesModel("Do I not destroy my enemies when I make them my friends?"));
                break;

            case "Coming Soon":
                Toast toast= Toast.makeText(mContext,
                        "Under Development", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                AppConstants.UDER_DEVELOPMENT = true;
                break;

            case "Imagination":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("You're only given a little spark of madness. You mustn't lose it."));
                quotesListActivityArrayList.add(new QuotesModel("The world of reality has its limits; the world of imagination is boundless."));
                quotesListActivityArrayList.add(new QuotesModel("Logic will get you from A to B. Imagination will take you everywhere."));
                quotesListActivityArrayList.add(new QuotesModel("Every block of stone has a statue inside it and it is the task of the sculptor to discover it."));
                quotesListActivityArrayList.add(new QuotesModel("I saw the angel in the marble and carved until I set him free."));
                quotesListActivityArrayList.add(new QuotesModel("The visionary starts with a clean sheet of paper, and re-imagines the world."));
                quotesListActivityArrayList.add(new QuotesModel("Everything you can imagine is real."));
                quotesListActivityArrayList.add(new QuotesModel("Reality leaves a lot to the imagination."));
                quotesListActivityArrayList.add(new QuotesModel("Happiness is not an ideal of reason, but of imagination."));
                quotesListActivityArrayList.add(new QuotesModel("Imagination creates reality."));
                quotesListActivityArrayList.add(new QuotesModel("Imagination and fiction make up more than three quarters of our real life."));
                quotesListActivityArrayList.add(new QuotesModel("Visualize this thing that you want, see it, feel it, believe in it. Make your mental blue print, and begin to build."));
                quotesListActivityArrayList.add(new QuotesModel("A hunch is creativity trying to tell you something."));
                quotesListActivityArrayList.add(new QuotesModel("Think left and think right and think low and think high. Oh, the thinks you can think up if only you try!"));
                quotesListActivityArrayList.add(new QuotesModel("Those who do not want to imitate anything, produce nothing."));
                quotesListActivityArrayList.add(new QuotesModel("I paint objects as I think them, not as I see them."));
                quotesListActivityArrayList.add(new QuotesModel("Justice is the truth in action."));
                quotesListActivityArrayList.add(new QuotesModel("The passions of the young are vices in the old."));
                quotesListActivityArrayList.add(new QuotesModel("It is easy to understand God as long as you don't try to explain him."));
                quotesListActivityArrayList.add(new QuotesModel("God is the place where I do not remember the rest."));
                quotesListActivityArrayList.add(new QuotesModel("Who ever has no fixed opinions has no constant feelings."));
                break;

            case "Life":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment."));
                quotesListActivityArrayList.add(new QuotesModel("He who has a why to live can bear almost any how."));
                quotesListActivityArrayList.add(new QuotesModel("Life is what happens while you are busy making other plans."));
                quotesListActivityArrayList.add(new QuotesModel("Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment."));
                quotesListActivityArrayList.add(new QuotesModel("We are here to add what we can to life, not to get what we can from life."));
                quotesListActivityArrayList.add(new QuotesModel("Life is a lively process of becoming."));
                quotesListActivityArrayList.add(new QuotesModel("Every man dies. Not every man really lives."));
                quotesListActivityArrayList.add(new QuotesModel("We do not remember days, we remember moments."));
                quotesListActivityArrayList.add(new QuotesModel("The truth is you don't know what is going to happen tomorrow. Life is a crazy ride, and nothing is guaranteed."));
                quotesListActivityArrayList.add(new QuotesModel("Nobody made a greater mistake than he who did nothing because he could do only a little."));
                quotesListActivityArrayList.add(new QuotesModel("Life is a dream for the wise, a game for the fool, a comedy for the rich, a tragedy for the poor."));
                quotesListActivityArrayList.add(new QuotesModel("The art of life is to know how to enjoy a little and to endure very much."));
                quotesListActivityArrayList.add(new QuotesModel("The only disability in life is a bad attitude."));
                quotesListActivityArrayList.add(new QuotesModel("Surprise is the greatest gift which life can grant us."));
                quotesListActivityArrayList.add(new QuotesModel("Good friends, good books and a sleepy conscience: this is the ideal life."));
                quotesListActivityArrayList.add(new QuotesModel("Life isn't about finding yourself. Life is about creating yourself."));
                quotesListActivityArrayList.add(new QuotesModel("Everything in life is luck."));
                quotesListActivityArrayList.add(new QuotesModel("Nobody made a greater mistake than he who did nothing because he could do only a little."));
                quotesListActivityArrayList.add(new QuotesModel("Life is 10 percent what you make it, and 90 percent how you take it."));
                quotesListActivityArrayList.add(new QuotesModel("Life isn't about finding yourself. Life is about creating yourself."));
                quotesListActivityArrayList.add(new QuotesModel("I have found that if you love life, life will love you back."));
                quotesListActivityArrayList.add(new QuotesModel("Good friends, good books and a sleepy conscience: this is the ideal life."));
                break;

            case "Sad":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Our sweetest songs are those that tell of saddest thought."));
                quotesListActivityArrayList.add(new QuotesModel("Nothing is more sad than the death of an illusion."));
                quotesListActivityArrayList.add(new QuotesModel("A sad soul can kill quicker than a germ."));
                quotesListActivityArrayList.add(new QuotesModel("First, accept sadness. Realize that without losing, winning isn't so great."));
                quotesListActivityArrayList.add(new QuotesModel("The sad truth is that most evil is done by people who never make up their minds to be good or evil."));
                quotesListActivityArrayList.add(new QuotesModel("Don't grieve. Anything you lose comes round in another form."));
                quotesListActivityArrayList.add(new QuotesModel("The word 'happy' would lose its meaning if it were not balanced by sadness."));
                quotesListActivityArrayList.add(new QuotesModel("The sad truth is that opportunity doesn't knock twice."));
                quotesListActivityArrayList.add(new QuotesModel("Good humor is the health of the soul, sadness is its poison."));
                quotesListActivityArrayList.add(new QuotesModel("Sad things happen. They do. But we don't need to live sad forever."));
                quotesListActivityArrayList.add(new QuotesModel("Delicious tears! The heart's own dew."));
                quotesListActivityArrayList.add(new QuotesModel("Our sweetest songs are those that tell of saddest thought."));
                quotesListActivityArrayList.add(new QuotesModel("The rose and the thorn, and sorrow and gladness are linked together."));
                quotesListActivityArrayList.add(new QuotesModel("The walls we build around us to keep sadness out also keeps out the joy."));
                quotesListActivityArrayList.add(new QuotesModel("It's a sad man my friend who's livin' in his own skin and can't stand the company."));
                quotesListActivityArrayList.add(new QuotesModel("One must not let oneself be overwhelmed by sadness."));
                quotesListActivityArrayList.add(new QuotesModel("Crying is cleansing. There's a reason for tears, happiness or sadness."));
                quotesListActivityArrayList.add(new QuotesModel("Red is the ultimate cure for sadness."));
                quotesListActivityArrayList.add(new QuotesModel("Everything's complicated, even those things that seem flat in their bleakness or sadness."));
                quotesListActivityArrayList.add(new QuotesModel("There is no sadder sight than a young pessimist."));
                quotesListActivityArrayList.add(new QuotesModel("I had sadness for breakfast."));
                break;

            case "Knowledge":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("The aim of education is the knowledge, not of facts, but of values."));
                quotesListActivityArrayList.add(new QuotesModel("To acquire knowledge, one must study; but to acquire wisdom, one must observe."));
                quotesListActivityArrayList.add(new QuotesModel("Without knowledge action is useless and knowledge without action is futile."));
                quotesListActivityArrayList.add(new QuotesModel("Consider your origins: you were not made to live as brutes, but to follow virtue and knowledge."));
                quotesListActivityArrayList.add(new QuotesModel("The true sign of intelligence is not knowledge but imagination."));
                quotesListActivityArrayList.add(new QuotesModel("The learning and knowledge that we have, is, at the most, but little compared with that of which we are ignorant."));
                quotesListActivityArrayList.add(new QuotesModel("The first step towards knowledge is to know that we are ignorant."));
                quotesListActivityArrayList.add(new QuotesModel("The only good is knowledge, and the only evil is ignorance."));
                quotesListActivityArrayList.add(new QuotesModel("We are all born ignorant, but one must work hard to remain stupid."));
                quotesListActivityArrayList.add(new QuotesModel("If knowledge can create problems, it is not through ignorance that we can solve them."));
                quotesListActivityArrayList.add(new QuotesModel("Real knowledge is to know the extent of one's ignorance."));
                quotesListActivityArrayList.add(new QuotesModel("Knowledge is of two kinds. We know a subject ourselves, or we know where we can find information upon it."));
                quotesListActivityArrayList.add(new QuotesModel("It is one of my sources of happiness never to desire a knowledge of other people's business."));
                quotesListActivityArrayList.add(new QuotesModel("There is much pleasure to be gained from useless knowledge."));
                quotesListActivityArrayList.add(new QuotesModel("Knowledge is soon changed, then lost in the mist, an echo half-heard."));
                quotesListActivityArrayList.add(new QuotesModel("Knowledge which is acquired under compulsion obtains no hold on the mind."));
                quotesListActivityArrayList.add(new QuotesModel("Our knowledge can only be finite, while our ignorance must necessarily be infinite."));
                quotesListActivityArrayList.add(new QuotesModel("Science is the father of knowledge, but opinion breeds ignorance."));
                quotesListActivityArrayList.add(new QuotesModel("Knowledge is knowing that we cannot know."));
                quotesListActivityArrayList.add(new QuotesModel("Those who have knowledge, don't predict. Those who predict, don't have knowledge."));
                quotesListActivityArrayList.add(new QuotesModel("A good decision is based on knowledge and not on numbers."));
                quotesListActivityArrayList.add(new QuotesModel("Knowledge is the eye of desire and can become the pilot of the soul."));
                quotesListActivityArrayList.add(new QuotesModel("The ability to perceive or think differently is more important than the knowledge gained."));
                quotesListActivityArrayList.add(new QuotesModel("Where there is shouting, there is no true knowledge."));
                break;

            case "Mom":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("Motherhood is the greatest thing and the hardest thing."));
                quotesListActivityArrayList.add(new QuotesModel("The heart of a mother is a deep abyss at the bottom of which you will always find forgiveness."));
                quotesListActivityArrayList.add(new QuotesModel("Motherhood has a very humanizing effect. Everything gets reduced to essentials."));
                quotesListActivityArrayList.add(new QuotesModel("The mother's heart is the child's schoolroom."));
                quotesListActivityArrayList.add(new QuotesModel("Yes, Mother. I can see you are flawed. You have not hidden it. That is your greatest gift to me."));
                quotesListActivityArrayList.add(new QuotesModel("My mother taught me to treat a lady respectfully."));
                quotesListActivityArrayList.add(new QuotesModel("I am truly my mother's son."));
                quotesListActivityArrayList.add(new QuotesModel("The man in our society is the breadwinner; the woman has enough to do as the homemaker, wife and mother."));
                quotesListActivityArrayList.add(new QuotesModel("Sweater, n.: garment worn by child when its mother is feeling chilly."));
                quotesListActivityArrayList.add(new QuotesModel("It is not until you become a mother that your judgment slowly turns to compassion and understanding."));
                quotesListActivityArrayList.add(new QuotesModel("There was never a child so lovely but his mother was glad to get him to sleep."));
                quotesListActivityArrayList.add(new QuotesModel("Only God Himself fully appreciates the influence of a Christian mother in the molding of character in her children."));
                quotesListActivityArrayList.add(new QuotesModel("The woman is uniformly sacrificed to the wife and mother."));
                quotesListActivityArrayList.add(new QuotesModel("Of all the roles I've played, none has been as fulfilling as being a mother."));
                quotesListActivityArrayList.add(new QuotesModel("Everyone checks out my mom. My mom's hot."));
                quotesListActivityArrayList.add(new QuotesModel("There are only two things a child will share willingly; communicable diseases and its mother's age."));
                quotesListActivityArrayList.add(new QuotesModel("My parents, especially my mother, were no influence on me whatsoever."));
                quotesListActivityArrayList.add(new QuotesModel("Mother is far too clever to understand anything she does not like."));
                quotesListActivityArrayList.add(new QuotesModel("I'm sure that my mom would have been happy with any path I chose."));
                quotesListActivityArrayList.add(new QuotesModel("Why do grandparents and grandchildren get along so well? The mother."));
                quotesListActivityArrayList.add(new QuotesModel("My mom taught us the Serenity Prayer at a young age."));
                quotesListActivityArrayList.add(new QuotesModel("I'd lose my mind if I heard my kid call the nanny Mommy."));
                quotesListActivityArrayList.add(new QuotesModel("What is free time? I'm a single mother. My free moments are filled with loving my little girl."));
                quotesListActivityArrayList.add(new QuotesModel("A woman must combine the role of mother, wife and politician."));
                quotesListActivityArrayList.add(new QuotesModel("I guess I was a mom so late in life, my daughter was the greatest thing since sliced bread."));
                quotesListActivityArrayList.add(new QuotesModel("My mom and my father's birthday are on the same day."));
                quotesListActivityArrayList.add(new QuotesModel("My mother thinks I could have even run a larger company."));
                quotesListActivityArrayList.add(new QuotesModel("Mothers don't let your daughters grow up to be models unless you're present."));
                break;

            case "Marriage":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("A successful marriage requires falling in love many times, always with the same person."));
                quotesListActivityArrayList.add(new QuotesModel("Marriages are made in heaven and consummated on Earth."));
                quotesListActivityArrayList.add(new QuotesModel("When a man steals your wife, there is no better revenge than to let him keep her."));
                quotesListActivityArrayList.add(new QuotesModel("When a man opens a car door for his wife, it's either a new car or a new wife."));
                quotesListActivityArrayList.add(new QuotesModel("It is not a lack of love, but a lack of friendship that makes unhappy marriages."));
                quotesListActivityArrayList.add(new QuotesModel("A successful marriage is an edifice that must be rebuilt every day."));
                quotesListActivityArrayList.add(new QuotesModel("When a man steals your wife, there is no better revenge than to let him keep her."));
                quotesListActivityArrayList.add(new QuotesModel("Let the wife make the husband glad to come home, and let him make her sorry to see him leave."));
                quotesListActivityArrayList.add(new QuotesModel("There were three of us in this marriage, so it was a bit crowded."));
                quotesListActivityArrayList.add(new QuotesModel("A happy marriage is a long conversation which always seems too short."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is good for those who are afraid to sleep alone at night."));
                quotesListActivityArrayList.add(new QuotesModel("Love is moral even without legal marriage, but marriage is immoral without love."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage has no guarantees. If that's what you're looking for, go live with a car battery."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage should be a duet - when one sings, the other claps."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is neither heaven nor hell, it is simply purgatory."));
                quotesListActivityArrayList.add(new QuotesModel("Husbands are like fires - they go out when they're left unattended."));
                quotesListActivityArrayList.add(new QuotesModel("All men make mistakes, but married men find out about them sooner."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is an adventure, like going to war."));
                quotesListActivityArrayList.add(new QuotesModel("Married men live longer than single men. But married men are a lot more willing to die."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is an adventure, like going to war."));
                quotesListActivityArrayList.add(new QuotesModel("Many a man in love with a dimple makes the mistake of marrying the whole girl."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is an exercise in torture."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage is not just spiritual communion, it is also remembering to take out the trash."));
                quotesListActivityArrayList.add(new QuotesModel("Marriage: A word which should be pronounced 'mirage'."));
                quotesListActivityArrayList.add(new QuotesModel("Any intelligent woman who reads the marriage contract, and then goes into it, deserves all the consequences."));
                quotesListActivityArrayList.add(new QuotesModel("Always get married in the morning. That way if it doesn't work out, you haven't wasted the whole day."));
                quotesListActivityArrayList.add(new QuotesModel("One should always be in love. That is the reason one should never marry."));
                quotesListActivityArrayList.add(new QuotesModel("When you have a baby, love is automatic, when you get married, love is earned."));
                quotesListActivityArrayList.add(new QuotesModel("No man is regular in his attendance at the House of Commons until he is married."));
                break;

            case "Romantic":
                quotesListActivityArrayList = new ArrayList<>();
                quotesListActivityArrayList.add(new QuotesModel("There is no charm equal to tenderness of heart."));
                quotesListActivityArrayList.add(new QuotesModel("Happy is the man whom the Muses love: sweet speech flows from his mouth."));
                quotesListActivityArrayList.add(new QuotesModel("Love recognizes no barriers. It jumps hurdles, leaps fences, penetrates walls to arrive at its destination full of hope."));
                quotesListActivityArrayList.add(new QuotesModel("Heard melodies are sweet, but those unheard are sweeter."));
                quotesListActivityArrayList.add(new QuotesModel("We are each of us angels with only one wing, and we can only fly by embracing one another.."));
                quotesListActivityArrayList.add(new QuotesModel("I think romance is anything honest. As long as it's honest, it's so disarming."));
                quotesListActivityArrayList.add(new QuotesModel("Romance is tempestuous. Love is calm."));
                quotesListActivityArrayList.add(new QuotesModel("So the lover must struggle for words."));
                quotesListActivityArrayList.add(new QuotesModel("When I give I give myself."));
                quotesListActivityArrayList.add(new QuotesModel("Love is the silent saying and saying of a single name."));
                quotesListActivityArrayList.add(new QuotesModel("Am I a romantic? I've seen 'Wuthering Heights' ten times. I'm a romantic."));
                quotesListActivityArrayList.add(new QuotesModel("Remember, beneath every cynic there lies a romantic, and probably an injured one."));
                quotesListActivityArrayList.add(new QuotesModel("I'm still a bit of a romantic and an idealist and hopelessly naive."));
                quotesListActivityArrayList.add(new QuotesModel("When you look at me, when you think of me, I am in paradise."));
                quotesListActivityArrayList.add(new QuotesModel("There's something about the sound of a train that's very romantic and nostalgic and hopeful."));
                quotesListActivityArrayList.add(new QuotesModel("And ever has it been known that love knows not its own depth until the hour of separation."));
                quotesListActivityArrayList.add(new QuotesModel("Beauty is the lover's gift."));
                quotesListActivityArrayList.add(new QuotesModel("Morning without you is a dwindled dawn."));
                quotesListActivityArrayList.add(new QuotesModel("Romance is the glamour which turns the dust of everyday life into a golden haze."));
                quotesListActivityArrayList.add(new QuotesModel("The sound of a kiss is not so loud as that of a cannon, but its echo lasts a great deal longer."));
                quotesListActivityArrayList.add(new QuotesModel("Kiss me and you will see how important I am."));
                quotesListActivityArrayList.add(new QuotesModel("I am certain of nothing but the holiness of the heart's affections, and the truth of imagination."));
                quotesListActivityArrayList.add(new QuotesModel("All the beautiful sentiments in the world weigh less than a single lovely action."));
                quotesListActivityArrayList.add(new QuotesModel("The word 'romance,' according to the dictionary, means excitement, adventure, and something extremely real. Romance should last a lifetime."));
                quotesListActivityArrayList.add(new QuotesModel("How far away the stars seem, and how far is our first kiss, and ah, how old my heart."));
                quotesListActivityArrayList.add(new QuotesModel("Love is friendship set on fire."));
                quotesListActivityArrayList.add(new QuotesModel("I love you - I am at rest with you - I have come home."));
                quotesListActivityArrayList.add(new QuotesModel("Love planted a rose, and the world turned sweet."));
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


            case "Nikola tesla":
                biographiesURI(1, "https://drive.google.com/file/d/13qp-y9SyEQfvvmDQuA29AjflTRqnoU2g/view?usp=sharing");
                break;
            case "Mother teresa":
                biographiesURI(1, "https://drive.google.com/file/d/1FakqOPxkgf_4kad52gidkg7MWg69HBPK/view?usp=sharing");
                break;
            case "Steve jobs":
                biographiesURI(1, "https://drive.google.com/file/d/1i4J3P8KgEINmPVCpQIqO_qJZnV11TNuN/view?usp=sharing");
                break;
            case "Swami vivekananda":
                biographiesURI(1, "https://drive.google.com/file/d/1vixS_vsTiUTeXdBFB1LnyJNPj50epeqm/view?usp=sharing");
                break;
            case "Sachin tendulkar":
                biographiesURI(1, "https://drive.google.com/file/d/1Bv_lWpD0TiM4BYB_53LgbF8I0311Iq9X/view?usp=sharing");
                break;
            case "Jack ma":
                biographiesURI(1, "https://drive.google.com/file/d/1zvV8wPwBkxb5ahWg914-5Y_0nhXaufdI/view?usp=sharing");
                break;
            case "Bill gates":
                biographiesURI(1, "https://drive.google.com/file/d/1g7szSuQPe5x-0JT1FQLnzojYjhxAERUK/view?usp=sharing");
                break;
            case "chanakya":
                biographiesURI(1, "https://drive.google.com/file/d/1y490xVnp0oXAUHz1DPZ6WJtEl7brhbM2/view?usp=sharing");
                break;
            case "Einstein":
                biographiesURI(1, "https://drive.google.com/file/d/1xaVvO8R9xWhrZguhK_xpB1_LlHDwgMFa/view?usp=sharing");
                break;
            case "Abdul kalam":
                biographiesURI(1, "https://drive.google.com/file/d/1pKL8nVDeQqnfhcb_wpoYQseykcbgWsu3/view?usp=sharing");
                break;
            case "Elon musk":
                biographiesURI(1, "https://drive.google.com/file/d/1f3I565Zn4YUh9Q_UTOmtbZwlTmLiqv9K/view?usp=sharing");
                break;
            case "Narendra modi":
                biographiesURI(1, "https://drive.google.com/file/d/1aEjoFk4AnpTTPByzgF39EX7NPc1pq_bN/view?usp=sharing");
                break;
            case "Vallabhbhai patel":
                biographiesURI(1, "https://drive.google.com/file/d/1X5TTkp3_w0ulZaqDwy3SjsRUtOaNsjtA/view?usp=sharing");
                break;
            case "Mahatma gandhi":
                biographiesURI(1, "https://drive.google.com/file/d/1baJPiEtka-WlifoJEdIcaVRYBywH5X2U/view?usp=sharing");
                break;

        }
        if (!AppConstants.UDER_DEVELOPMENT){
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
        }
        AppConstants.ISURI = 0;
        AppConstants.UDER_DEVELOPMENT = false;
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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}