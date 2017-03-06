package com.engine;

import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class RuleRunner {


    public RuleRunner() {

    }


    public StatefulKnowledgeSession getSession(String[] rules) throws Exception {


        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();


        for ( int i = 0; i < rules.length; i++ ) {

            String ruleFile = rules[i];

            System.out.println( "Loading file: " + ruleFile );

            kbuilder.add( ResourceFactory.newClassPathResource( ruleFile,

                                                                RuleRunner.class ),

                          ResourceType.DRL );

        }


        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();

        kbase.addKnowledgePackages( pkgs );

        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        return ksession;

    }

}