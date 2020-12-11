package net.jcip.examples.part1_base.chapter3_objectshare;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Animals

 当访问共享的可变数据时通常需要同步
 一种避免使用同步的方式就是不共享数据，如果仅在单线程内访问数据就不需要同步，这种技术即线程封闭--实现线程安全的最简单方式之一

 使用线程封闭的例子：Swing框架、JDBC的connection对象

 栈封闭：只能通过局部变量才能访问对象  ---线程封闭的一种特例

 */


//基本类型的局部变量与引用变量的线程封闭性

public class Animals {
    Ark ark;
    Species species;
    Gender gender;

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;  //引用类型变量被封闭在局部变量中
        int numPairs = 0;   //基本类型的局部变量始终封闭在线程内（任何方法都无法获得对基本类型的引用）
        Animal candidate = null;

        // animals被封闭在方法中，不要使它们逸出
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a))
                candidate = a;
            else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }


    class Animal {
        Species species;
        Gender gender;

        public boolean isPotentialMate(Animal other) {
            return species == other.species && gender != other.gender;
        }
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum Gender {
        MALE, FEMALE
    }

    class AnimalPair {
        private final Animal one, two;

        public AnimalPair(Animal one, Animal two) {
            this.one = one;
            this.two = two;
        }
    }

    class SpeciesGenderComparator implements Comparator<Animal> {
        public int compare(Animal one, Animal two) {
            int speciesCompare = one.species.compareTo(two.species);
            return (speciesCompare != 0)
                    ? speciesCompare
                    : one.gender.compareTo(two.gender);
        }
    }

    class Ark {
        private final Set<AnimalPair> loadedAnimals = new HashSet<AnimalPair>();

        public void load(AnimalPair pair) {
            loadedAnimals.add(pair);
        }
    }
}


