package com.example.covid19alert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.covid19alert.QuizContract.*;
import com.example.covid19alert.Question;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract. QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("Who do you meet first after developing symptoms of Covid19?",
                "parent", "doctor", "therapist", 2);
        addQuestion(q1);
        Question q2 = new Question("Where was the first case of Covid19 discovered in the world?",
                "Wuhan, China", "Maryland, USA", "Lagos, Nigeria", 1);
        addQuestion(q2);
        Question q3 = new Question("What month did the outbreak begin?",
                "January", "December", "September", 2);
        addQuestion(q3);
        Question q4 = new Question("One of these is NOT a common symptom of Covid19?",
                "mild fever", "loss of taste/smell", "teeth decay", 3);
        addQuestion(q4);
        Question q5 = new Question("Is it possible to catch Covid19 from someone who has just a mild cough and does not feel ill?",
                "yes", "no", "maybe", 1);
        addQuestion(q5);
        Question q6 = new Question("An important measure taken by those who have Covid19 symptoms to avoid infecting others is called?",
                "sick-leave", "self-isolation", "community protection", 2);
        addQuestion(q6);
        Question q7 = new Question("Can i catch Covid19 from my pets",
                "maybe", "no", "yes", 2);
        addQuestion(q7);
        Question q8 = new Question("A patient can recover from the Covid19 without needing hospital treatment?",
                "no", "yes", "maybe", 2);
        addQuestion(q8);
        Question q9 = new Question("Which of these ways is NOT the best to protect others and yourself?",
                "maintain close physical contacts", "wash your hands", "practise respiratory hygiene", 1);
        addQuestion(q9);
        Question q10 = new Question("Close contact means?",
                "less than 1 meter", "less/more 2 meters", "less than 3 meters", 1);
        addQuestion(q10);
        Question q11 = new Question("When should you self isolate?",
                "if you have been exposed", "if you have dry Cough", "if you can't breath", 2);
        addQuestion(q11);
        Question q12 = new Question("One of these is most proper, after developing symptoms?",
                "self meditate and quarantine", "contact your doctor", "self-isolate and monitor", 3);
        addQuestion(q12);
        Question q13 = new Question("What do you do after exposure to an infected person?",
                "lock down", "self quarantine", "self-isolate", 2);
        addQuestion(q13);
        Question q14 = new Question("Define quarantine?",
                "separating people who are ill themselves but may have been exposed", "separating people who are not ill themselves but may have been exposed", "separating people who are not" +
                " ill themselves and have not been exposed", 2);
        addQuestion(q14);
        Question q15 = new Question("How long should you self-isolate or self quarantine?",
                "7 days", "12 days", "14 days", 3);
        addQuestion(q15);
        Question q16 = new Question("Regularly clean your hands with a/an _ handrub?",
                "water based", "alcohol based", "chlorine based", 2);
        addQuestion(q16);
        Question q17 = new Question("Can children catch Covid19?",
                "yes", "no", "maybe", 1);
        addQuestion(q17);
        Question q18 = new Question("One of these is an incorrect Covid19 precaution?",
                "avoid going to crowded places", "avoid touching eyes, nose and mouth", "avoid immediate family members ", 3);
        addQuestion(q18);
        Question q19 = new Question("How long does the Coronavirus survive on surfaces?",
                "48hrs", "72hrs", "24hrs", 2);
        addQuestion(q19);
        Question q20 = new Question("Is antibiotics effective for preventing or treating Coronavirus?",
                "yes", "no", "maybe", 2);
        addQuestion(q20);


    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
