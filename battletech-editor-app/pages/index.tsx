// Placeholder for Home Page - pages/index.tsx
import Link from 'next/link';
import Layout from '../components/common/Layout';

export default function HomePage() {
  return (
    <Layout>
      <h1 className="text-2xl font-bold mb-4">BattleTech Unit and Equipment Browser</h1>
      <nav className="flex flex-col space-y-2">
        <Link href="/units" className="text-blue-500 hover:underline">
          View Units
        </Link>
        <Link href="/equipment" className="text-blue-500 hover:underline">
          View Equipment
        </Link>
      </nav>
    </Layout>
  );
}
