package net.jcip.examples.part1_base.chapter3_objectshare;

import java.util.HashSet;
import java.util.Set;

/**
 * Secrets

 发布一个对象


 */
class Secrets {
    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
    }
}


class Secret {
}
