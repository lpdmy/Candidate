package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import controller.CandidateManager;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import view.Menu;
import view.Validation;

public class CompanyManager extends Menu<String> {

    static String[] menu = {"Experience", "Fresher", "Intern", "Searching", "Exit"};
    private CandidateManager list = new CandidateManager();
    Validation val = new Validation();

    public CompanyManager() {
        super("Candidate Management System", menu);
    }
//--------------------------------------------------

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                list.addCandidate(inputCandidate(1));
                break;
            case 2:
                list.addCandidate(inputCandidate(2));
                break;
            case 3:
                list.addCandidate(inputCandidate(3));
                break;
            case 4:
                searching();
                break;
            case 5:
                System.exit(0);
        }
    }
//--------------------------------------------------

    private String getValue(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.nextLine();
    }
//--------------------------------------------------

    private Candidate inputCandidate(int a) {
        String candidateId = getValue("Input ID: ");
        String firstName = getValue("Input first name: ");
        String lastName = getValue("Input last name: ");
        Date birDate = null;
        try {
            while (birDate == null) {
                birDate = val.checkValidDate(getValue("Input birthdate: "));
                if (birDate == null) {
                    System.out.println("Please input again in the appropriate format (xx/yy/zzzz): ");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String address = getValue("Input address: ");
        String phone = null;
        try {
            while (phone == null) {
                phone = val.checkPhone(getValue("Input phone number:  "));
                if (phone == null) {
                    System.out.println("Please input again in the appropriate format: ");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String email = null;
        try {
                    while (email == null) {
                        email = val.checkMail(getValue("Input email: "));
                        if (email == null) {
                            System.out.println("Please input again in the appropriate format: ");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
        String candidateType;
        switch (a) {
            case 1:
                candidateType = "Experience";
                int year = -1;
                try {
                    while (year == -1) {
                        year = val.checkInt(getValue("Input year experienced: "));
                        if (year == -1) {
                            System.out.println("Please input again in the appropriate format (0-100): ");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                String proSkill = getValue("Input professional skill: ");
                return new Experience(year, proSkill, candidateId, firstName, lastName, birDate, address, phone, email, candidateType);
            case 2:
                candidateType = "Fresher";
                Date graYear = null;
                try {
                    while (graYear == null) {
                        graYear = val.checkValidDate(getValue("Input year experienced: "));
                        if (graYear == null) {
                            System.out.println("Please input again in the appropriate format (0-100): ");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                String rank = null;
                try {
                    while (rank == null) {
                        rank = val.checkRank(getValue("Input rank: "));
                        if (rank == null) {
                            System.out.println("Please input again in the appropriate format (Good, Excellence, Fair, Poor): ");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                String education = getValue("Input university: ");
                return new Fresher(graYear, rank, education, candidateId, firstName, lastName, birDate, address, phone, email, candidateType);
            case 3:
                candidateType = "Intern";
                String major = getValue("Input major: ");
                String semester = getValue("Input semester: ");
                String university = getValue("Input university: ");
                return new Intern(major, semester, university, candidateId, firstName, lastName, birDate, address, phone, email, candidateType);
        }
        return null;
    }
//-------------------------------------------------------------------------------------------

    private void searching() {
                list.listing();
                ArrayList<Candidate> rs = null;
                String input1;
                int input2;
                String type;

                input1 = getValue("Enter first name or last name:");
                input2 = Integer.parseInt(getValue("Enter CandidateType (0: Experience,1: Fresher or 2: Intern): "));
                switch (input2) {
                    case 0:
                        type = "Experience";
                        break;
                    case 1:
                        type = "Fresher";
                        break;
                    case 2:
                        type = "Intern";
                        break;
                    default:
                        type = null;
                        break;
                }
                rs = list.search(c -> (c.getFirstName().equalsIgnoreCase(input1) || c.getLastName().equalsIgnoreCase(input1)) && c.getCandidateType().equalsIgnoreCase(type));

                if (rs != null) {
                    list.display(rs);
                }

            }

    }


